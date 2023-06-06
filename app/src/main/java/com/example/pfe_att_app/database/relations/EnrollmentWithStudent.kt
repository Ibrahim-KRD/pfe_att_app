package com.example.pfe_att_app.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pfe_att_app.domain.entities.Enrollment
import com.example.pfe_att_app.domain.entities.Student

data class EnrollmentWithStudent (

    @Embedded val enrollment: Enrollment,
    @Relation(
        parentColumn = "student_id",
        entityColumn = "id"
    )
    val student: Student
        )