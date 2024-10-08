package com.example.coffeetruckfinalproject11.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeetruckfinalproject11.database.Database
import com.example.coffeetruckfinalproject11.model.CoffeeTruck
import com.example.coffeetruckfinalproject11.model.dto.CoffeeTruckCreationForm
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.launch

class CoffeeTruckViewModel : ViewModel() {

    val user = Database.getInstance().getUser()
    val exception = MutableLiveData<Exception>()
    val loadingState = MutableLiveData<LoadingState>(LoadingState.Loaded)

    private val _coffeeTrucks = MutableLiveData<List<CoffeeTruck>>(listOf())
    val coffeeTrucks: LiveData<List<CoffeeTruck>> = _coffeeTrucks

    private var coffeeTrucksListenerRegistration: ListenerRegistration? = null

    init {
        Database.getInstance().startListeningToUser()
        coffeeTrucksListenerRegistration = Database.getInstance().listenCoffeeTrucks(_coffeeTrucks)
    }

    fun logOut()  {
        Database.getInstance().logout()
    }

    fun addReview(truck:CoffeeTruck, review:String, callback: () -> Unit)
    {
        viewModelScope.launch { //launch so it won't finish before it's time
            try {
                truck.reviews.add(review)
                loadingState.postValue(LoadingState.Loading) //waiting
                Database.getInstance().saveCoffeeTruck(truck)

            } catch (e: Exception) {
                truck.reviews.remove(review)
                exception.postValue(e)

            } finally {
                loadingState.postValue(LoadingState.Loaded)
                callback()
            }
        }
    }

    fun addCoffeeTruck(coffeeTruckForm: CoffeeTruckCreationForm, callback: () -> Unit) {
        viewModelScope.launch { //launch so it won't finish before it's time
            try {
                loadingState.postValue(LoadingState.Loading) //waiting
                Database.getInstance().addCoffeeTruck(coffeeTruckForm)
            } catch (e: Exception) {
                exception.postValue(e)
            } finally {
                loadingState.postValue(LoadingState.Loaded)
                callback()
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        Database.getInstance().stopListeningToUser()
        coffeeTrucksListenerRegistration?.remove()
    }


}

