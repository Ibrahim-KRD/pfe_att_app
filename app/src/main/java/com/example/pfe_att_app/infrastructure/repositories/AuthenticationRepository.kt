package com.example.pfe_att_app.infrastructure.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.pfe_att_app.data.Resource
import com.example.pfe_att_app.data.await
import com.example.pfe_att_app.database.TeacherDao
import com.example.pfe_att_app.domain.entities.Person
import com.example.pfe_att_app.domain.entities.Teacher
import com.example.pfe_att_app.domain.repositories.IAuthenticationRepository
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
   private val teacherDao: TeacherDao
) : IAuthenticationRepository {


    private val coroutineScope = CoroutineScope(Dispatchers.Main)



    override  fun LogIn(email: String, password: String): LiveData<Teacher> {
        coroutineScope.launch(Dispatchers.IO) {
            teacherDao.getTeacher()
        }

       return teacherDao.getTeacher()
    }

    override  fun Register(teacher: Teacher){
        coroutineScope.launch(Dispatchers.IO) {
            teacherDao.Insert(teacher)
        }
    }




    override fun LogOut() {

    }

}