package com.example.pfe_att_app.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pfe_att_app.domain.entities.Enrollment
import com.example.pfe_att_app.domain.entities.Module
import com.example.pfe_att_app.domain.entities.Seance
import com.example.pfe_att_app.domain.entities.Student

data class EnrollmentWithSeanceStudentModule(

    @Embedded val enrollment: Enrollment,
    @Relation(
        parentColumn = "seance_id",
        entityColumn = "id"
    )
    val seance: Seance,
    @Relation(
        parentColumn = "student_id",
        entityColumn = "id"
    )
    val student: Student

)
