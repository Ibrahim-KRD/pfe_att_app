package com.example.pfe_att_app.infrastructure.repositories

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pfe_att_app.database.StudentDao
import com.example.pfe_att_app.database.TeacherDao
import com.example.pfe_att_app.domain.entities.Person
import com.example.pfe_att_app.domain.entities.Student
import com.example.pfe_att_app.domain.entities.Teacher
import com.example.pfe_att_app.domain.repositories.IAuthenticationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val teacherDao: TeacherDao,
    private val studentDao: StudentDao
) : IAuthenticationRepository {


    private val coroutineScope = CoroutineScope(Dispatchers.Main)



    override fun TeacherLogIn(email: String, password: String): Teacher? {
        var teacher: Teacher? = null
        runBlocking {
            withContext(Dispatchers.IO) {
                teacher = teacherDao.getTeacherWithCredential(email, password)
            }
        }
        return teacher
    }

    // Student authentication
    override suspend fun StudentLogIn(email: String, password: String): Student? {
        var student :Student? = null
        runBlocking {
            withContext(Dispatchers.IO) {
            student = studentDao.getStudentWithCredential(email, password)
        }}
        return student
    }



    //region teacher authentiaction part




    override fun TeacherRegister(teacher: Teacher) {
        coroutineScope.launch(Dispatchers.IO) {
            teacherDao.Insert(teacher)
        }
    }


    // endregion




    override fun StudentRegister(student: Student) {
        coroutineScope.launch(Dispatchers.IO) {
            studentDao.Insert(student)
        }
    }

    // endregion


    override fun LogOut() {

    }

}