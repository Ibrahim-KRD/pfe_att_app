package com.example.pfe_att_app.presenter.pages.authentication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe_att_app.domain.entities.Teacher
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
class AuthenticationViewModel @Inject constructor(val authenticationRepository: AuthenticationRepository) :
    ViewModel() {



    fun login(email: String, password: String):LiveData<Teacher> {

         return   authenticationRepository.LogIn(email, password)

    }


    fun logout() {
        //todo    : handle the logout here
    }


    fun register(teacher: Teacher) {
        // Simulating register process
        viewModelScope.launch {
            authenticationRepository.Register(

                teacher


                )
        }
    }


}