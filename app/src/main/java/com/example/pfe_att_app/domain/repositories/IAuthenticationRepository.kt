package com.example.pfe_att_app.domain.repositories

import androidx.lifecycle.LiveData
import com.example.pfe_att_app.data.Resource
import com.example.pfe_att_app.domain.entities.Person
import com.example.pfe_att_app.domain.entities.Teacher
import com.google.firebase.auth.FirebaseUser

interface IAuthenticationRepository {

 fun LogIn(email:String,password:String):LiveData<Teacher>
 fun Register(teacher: Teacher)

fun LogOut()
}