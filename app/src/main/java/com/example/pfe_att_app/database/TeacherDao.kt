package com.example.pfe_att_app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pfe_att_app.domain.entities.Teacher

@Dao
interface TeacherDao {

    @Insert
    suspend fun Insert(teacher: Teacher)

    @Delete
    suspend fun Delete(teacher: Teacher)

    @Update
    suspend fun Update(teacher: Teacher)

    @Query("Select * from teacher where id=1")
    fun getTeacher():LiveData<Teacher>

}