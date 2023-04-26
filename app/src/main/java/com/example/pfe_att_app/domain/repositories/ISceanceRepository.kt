package com.example.pfe_att_app.domain.repositories

import com.example.pfe_att_app.domain.entities.Sceance
import com.example.pfe_att_app.domain.entities.Student

interface ISceanceRepository {

    suspend fun getSceance(id: String): Sceance
    suspend fun AddStudentToSceance(student: Student)
    suspend fun RemoveStudentFromSceance(student: Student)
}