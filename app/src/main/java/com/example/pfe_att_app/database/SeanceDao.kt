package com.example.pfe_att_app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.pfe_att_app.database.relations.SceancewithResponsibleAndModule
import com.example.pfe_att_app.database.relations.SeanceWithModule
import com.example.pfe_att_app.domain.entities.Enrollment
import com.example.pfe_att_app.domain.entities.Seance

@Dao
interface SeanceDao {

    @Insert
    suspend fun Insert(sceance: Seance)

    @Update
    suspend fun Update(seance: Seance)

    @Delete
    suspend fun Delete(seance: Seance)


    @Transaction
    @Insert
    fun insertSeanceWithEnrollments(seance: Seance, enrollments: List<Enrollment>)


    @Transaction
    @Query("SELECT * FROM seance ")
    fun getSeanceWithResponsibleAndModule(): LiveData<List<SceancewithResponsibleAndModule>>

    @Transaction
    @Query("SELECT * FROM seance s , enrollments e where s.id = e.seance_id and e.student_id = :studentId ")
    fun getSeanceWithResponsibleAndModuleForStudent(studentId:Int): LiveData<List<SceancewithResponsibleAndModule>>

    @Transaction
    @Query("SELECT * FROM seance where day = :date")
    fun getSeanceWithResponsibleAndModuleForTeacher(date:String): List<SceancewithResponsibleAndModule?>?


    @Query("Select * from seance where id = :id")
    fun getSeanceById(id: Int): LiveData<Seance>


    @Transaction
    @Query("SELECT * FROM Seance where id = :seance_id ")
    fun getSeancesWithModule(seance_id:Int): SeanceWithModule?

    @Query("SELECT MAX(id) from seance")
    fun getLastSavedseance():Int?

}