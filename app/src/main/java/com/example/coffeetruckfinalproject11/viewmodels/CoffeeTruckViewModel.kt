package com.example.coffeetruckfinalproject11.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeetruckfinalproject11.database.Database
import com.example.coffeetruckfinalproject11.models.CoffeeTruck
import com.example.coffeetruckfinalproject11.models.dto.CoffeeTruckCreationForm
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

    fun addCoffeeTruck(coffeeTruckForm: CoffeeTruckCreationForm, callback: () -> Unit) {
        viewModelScope.launch {
            try {
                loadingState.postValue(LoadingState.Loading)
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

