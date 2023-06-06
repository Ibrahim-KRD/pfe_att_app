package com.example.pfe_att_app.infrastructure.repositories

import androidx.lifecycle.LiveData
import com.example.pfe_att_app.database.StudentDao
import com.example.pfe_att_app.database.TeacherDao
import com.example.pfe_att_app.domain.entities.Student
import com.example.pfe_att_app.domain.entities.Teacher
import com.example.pfe_att_app.domain.repositories.IAuthenticationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val teacherDao: TeacherDao,
    private val studentDao: StudentDao
) : IAuthenticationRepository {


    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    override fun TeacherLogIn(email: String, password: String): LiveData<Teacher> {

        return teacherDao.getTeacherWithCredential(email,password)
    }

    override fun TeacherRegister(teacher: Teacher) {
        coroutineScope.launch(Dispatchers.IO) {
            teacherDao.Insert(teacher)
        }
    }

    override fun StudentLogIn(email: String, password: String): LiveData<Student> {
       return studentDao.getStudentWithCredential(email, password)
    }

    override fun StudentRegister(student: Student) {
        coroutineScope.launch(Dispatchers.IO) {
            studentDao.Insert(student)
        }
    }


    override fun LogOut() {

    }

}