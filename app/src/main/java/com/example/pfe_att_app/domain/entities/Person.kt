package com.example.pfe_att_app.domain.entities

import androidx.room.PrimaryKey


open class Person(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var firstName: String,
    var lastName: String,
    // TODO:     contact: Contact,
    var adress: String,
    // TODO:   account: Account,
    var subscriptionYear: String
) {

}