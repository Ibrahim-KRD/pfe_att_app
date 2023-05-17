package com.example.pfe_att_app.domain.repositories

import com.example.pfe_att_app.domain.entities.Person

interface IAuthenticationRepository {
fun LogIn(email:String,password:String):Boolean
fun Register(person: Person) :Boolean
}