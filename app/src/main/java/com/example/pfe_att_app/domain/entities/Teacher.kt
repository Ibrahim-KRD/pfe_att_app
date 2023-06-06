package com.example.pfe_att_app.domain.entities

import androidx.room.Entity

// todo :  change the speciality and the role to enum
@Entity
class Teacher(

    firstName: String,
    lastName: String,
    adress: String,
    subscriptionYear: String,
    var speciality: String,
    var role: String,
    email: String,
    password: String
) : Person(
    firstName = firstName, lastName = lastName,
    adress = adress,
    subscriptionYear = subscriptionYear,
    password = password,
    email = email
)