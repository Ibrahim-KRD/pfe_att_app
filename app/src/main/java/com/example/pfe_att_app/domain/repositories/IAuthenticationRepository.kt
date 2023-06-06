package com.example.pfe_att_app.domain.repositories

import androidx.lifecycle.LiveData
import com.example.pfe_att_app.domain.entities.Student
import com.example.pfe_att_app.domain.entities.Teacher

interface IAuthenticationRepository {

 //region teacher

 fun TeacherLogIn(email:String, password:String):LiveData<Teacher>
 fun TeacherRegister(teacher: Teacher)

 //endregion


 //region student
 fun StudentLogIn(email:String, password:String):LiveData<Student>
 fun StudentRegister(student: Student)
 //endregion

fun LogOut()
}