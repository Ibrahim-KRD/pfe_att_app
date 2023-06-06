package com.example.pfe_att_app.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity
 class Module(
  @PrimaryKey(autoGenerate = true)
  val id : Int = 0,
  var  name: String,
  var  description: String,
    // TODO:   creationDate: Date,
   var level: String,
  var  speciality: String
)