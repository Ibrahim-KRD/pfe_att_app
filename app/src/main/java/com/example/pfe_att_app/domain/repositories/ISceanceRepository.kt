package com.example.pfe_att_app.domain.repositories

import com.example.pfe_att_app.domain.entities.Seance
import com.example.pfe_att_app.domain.entities.Student

interface ISceanceRepository {

    suspend fun getSceance(id: String): Seance
    suspend fun AddStudentToSceance(student: Student)
    suspend fun RemoveStudentFromSceance(student: Student)
}