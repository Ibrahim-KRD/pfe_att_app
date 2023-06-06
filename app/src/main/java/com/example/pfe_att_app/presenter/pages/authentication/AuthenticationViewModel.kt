package com.example.pfe_att_app.presenter.pages.authentication

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe_att_app.domain.entities.Person
import com.example.pfe_att_app.domain.entities.Student
import com.example.pfe_att_app.domain.entities.Teacher
import com.example.pfe_att_app.infrastructure.repositories.AuthenticationRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(val authenticationRepository: AuthenticationRepository) :
    ViewModel() {

    fun login(email: String, password: String): Person? {
var teacher :Teacher?=null
var student :Student?=null

       viewModelScope.launch {
             teacher = authenticationRepository.TeacherLogIn(email, password)
             student = authenticationRepository.StudentLogIn(email, password)
       }
        if (teacher != null) {
            return teacher
        }
        if (student != null) return student
        return null

    }
    fun logout() {
        //todo    : handle the logout here
    }


    fun StudentRegister(student: Student) {
        // Simulating register process
        viewModelScope.launch {
            authenticationRepository.StudentRegister(
                student
            )
        }
    }


    fun TeacherRegister(teacher: Teacher) {
        // Simulating register process
        viewModelScope.launch {
            authenticationRepository.TeacherRegister(
                teacher
            )
        }
    }


}
