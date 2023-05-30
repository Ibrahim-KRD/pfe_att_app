package com.example.pfe_att_app.presenter.pages.authentication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe_att_app.domain.repositories.IAuthenticationRepository
import com.example.pfe_att_app.infrastructure.repositories.AuthenticationRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toSet
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val authenticationRepository: IAuthenticationRepository ) :
    ViewModel() {

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated = _isAuthenticated.asStateFlow()



    private val _showError = MutableStateFlow(false)
    val showError = _showError.asStateFlow()

    fun login(email: String, password: String) {
        // Simulating login process
        viewModelScope.launch {
            delay(2000) // Simulating network delay

            // Perform login logic here
            val loginSuccess = performLogin(email, password)

            if (loginSuccess) {
                _isAuthenticated.emit(true)
            } else {
                _showError.emit(true)
            }
        }
    }


fun logout(){
  //todo    : handle the logout here
}



    fun register(email: String, password: String) {
        // Simulating register process
        viewModelScope.launch {
            delay(2000) // Simulating network delay

            // Perform register logic here
            val registerSuccess = performRegister(email, password)

            if (registerSuccess) {
                _isAuthenticated.emit(true)
            } else {
                _showError.emit(true)
            }
        }
    }

    private suspend fun performLogin(email: String, password: String): Boolean {
        // Perform actual login logic here
        // Return true if login is successful, false otherwise
        return true // Simulating login failure
    }

    private suspend fun performRegister(email: String, password: String): Boolean {
        // Perform actual register logic here
        // Return true if register is successful, false otherwise
        return true // Simulating register success
    }

}