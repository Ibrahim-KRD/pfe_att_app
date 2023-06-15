package com.example.pfe_att_app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.pfe_att_app.domain.entities.Student

@Dao
interface StudentDao {

    @Insert
    suspend fun Insert(student: Student)

    @Update
    suspend fun Update(student: Student)

    @Query("SELECT * FROM student where email = :email and password = :password")
    fun getStudentWithCredential(email: String, password: String): Student?


    @Query("SELECT * FROM student")
    fun getStudent():List<Student?>
}