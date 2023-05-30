package com.example.pfe_att_app.domain.repositories

import com.example.pfe_att_app.data.Resource
import com.example.pfe_att_app.domain.entities.Person
import com.google.firebase.auth.FirebaseUser

interface IAuthenticationRepository {
    val currentUser:FirebaseUser?
suspend fun LogIn(email:String,password:String):Resource<FirebaseUser>
suspend fun Register(email: String,password: String,name:String) :Resource<FirebaseUser>

fun LogOut()
}