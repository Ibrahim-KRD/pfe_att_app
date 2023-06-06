package com.example.pfe_att_app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pfe_att_app.domain.entities.Module

@Dao
interface ModuleDao {

    @Insert
    suspend fun Insert(module: Module)

    @Update
    suspend fun Update(module: Module)

    @Delete
    suspend fun Delete(module: Module)

    @Query("SELECT * FROM MODULE")
    fun getAllModule():LiveData<List<Module>>

}