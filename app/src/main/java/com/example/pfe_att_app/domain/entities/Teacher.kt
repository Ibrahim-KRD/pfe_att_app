package com.example.pfe_att_app.domain.entities

import androidx.room.Entity

// todo :  change the speciality and the role to enum
@Entity
class Teacher(

    firstName: String,
    lastName: String,
    // TODO:    contact: Contact,
    adress: String,
    // TODO:   account: Account,
    subscriptionYear: String,
  var  speciality : String,
   var role : String
) : Person(
   firstName =  firstName, lastName =  lastName,
   adress =  adress,
   subscriptionYear =  subscriptionYear
)