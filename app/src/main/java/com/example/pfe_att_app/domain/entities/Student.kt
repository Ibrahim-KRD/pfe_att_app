package com.example.pfe_att_app.domain.entities

class Student(
    firstName: String,
    lastName: String,

    adress: String,

    subscriptionYear: String,
    matricule: String,
    level: String,
    group: Int,
    speciality: String
) : Person(
    firstName,
    lastName,

    adress,

    subscriptionYear
)