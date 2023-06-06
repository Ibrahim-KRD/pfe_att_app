package com.example.pfe_att_app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.pfe_att_app.database.relations.SceancewithResponsibleAndModule
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
    @Query("SELECT * FROM seance")
    fun getSeanceWithResponsibleAndModule(): LiveData<List<SceancewithResponsibleAndModule>>

    @Query("Select * from seance where id = :id")
    fun getSeanceById(id: Int): LiveData<Seance>
}