package com.example.coffeetruckfinalproject11.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeetruckfinalproject11.database.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class LoadingState {
    data object Loading : LoadingState()
    data object Loaded : LoadingState()
}

class AuthViewModel : ViewModel() {

    val user = Database.getInstance().getUser()
    val exception = MutableLiveData<Exception>()
    val loadingState = MutableLiveData<LoadingState>(LoadingState.Loaded)

    init {
        Database.getInstance().startListeningToUser()
    }

    fun login(email: String, password: String, callback: () -> Unit) {
        viewModelScope.launch {
            try {
                loadingState.postValue(LoadingState.Loading)
                Database.getInstance().login(email, password)
            } catch (e: Exception) {
                exception.postValue(e)
            } finally {
                loadingState.postValue(LoadingState.Loaded)
                callback()
            }
        }
    }


    fun register(form: UserRegistrationForm, callback: () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                loadingState.postValue(LoadingState.Loading)
                val user = Database.getInstance().register(
                    form,
                    viewModelScope
                )
            } catch (e: Exception) {
                exception.postValue(e)
            } finally {
                loadingState.postValue(LoadingState.Loaded)
                callback()
            }
        }
    }

    //fun register(form: UserRegistrationForm) {

    }

