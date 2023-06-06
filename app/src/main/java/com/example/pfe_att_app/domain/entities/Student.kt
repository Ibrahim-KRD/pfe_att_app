package com.example.pfe_att_app.domain.entities

import androidx.room.Entity

@Entity
class Student(
    firstName: String,
    lastName: String,

    adress: String,

    subscriptionYear: String,
    var matricule: String,
    var level: String,
    var group: Int,
    var speciality: String
) : Person(
    firstName = firstName,
    lastName = lastName,

    adress = adress,

    subscriptionYear = subscriptionYear
)