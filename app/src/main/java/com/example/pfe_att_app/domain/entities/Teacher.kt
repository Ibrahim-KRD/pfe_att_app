package com.example.pfe_att_app.domain.entities
// todo :  change the speciality and the role to enum
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
    firstName, lastName,
    adress,
    subscriptionYear
) {


}