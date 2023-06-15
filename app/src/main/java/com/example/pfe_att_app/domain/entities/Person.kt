package com.example.pfe_att_app.domain.entities

import androidx.room.PrimaryKey


open class Person(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var firstName: String,
    var lastName: String,
    var adress: String,
    var subscriptionYear: String,

    var email: String,
    var password: String,
    var phone: String
)