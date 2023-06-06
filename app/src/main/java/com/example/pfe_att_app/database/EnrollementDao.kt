package com.example.pfe_att_app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.pfe_att_app.database.relations.EnrollmentWithStudent
import com.example.pfe_att_app.domain.entities.Enrollment

@Dao
interface EnrollementDao {

    @Transaction
    @Query("SELECT * FROM Enrollment")
    fun getEnrollmentsOfClass(): LiveData<List<EnrollmentWithStudent>>

    @Insert
    suspend fun Insert(enrollment: Enrollment)
}