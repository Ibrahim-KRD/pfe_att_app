package com.example.pfe_att_app.domain.repositories

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import com.example.pfe_att_app.domain.entities.Person
import com.example.pfe_att_app.domain.entities.Student
import com.example.pfe_att_app.domain.entities.Teacher

interface IAuthenticationRepository {

 //region teacher

 fun TeacherLogIn(email:String, password:String): Teacher?
 fun TeacherRegister(teacher: Teacher)

 //endregion


 //region student
 suspend fun StudentLogIn(email:String, password:String): Student?
 fun StudentRegister(student: Student)
 //endregion

fun LogOut()
}