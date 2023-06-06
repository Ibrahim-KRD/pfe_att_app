package com.example.pfe_att_app.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id :Int =0,
    var phoneNumber: String,
    var email: String
)