package com.example.pfe_att_app.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pfe_att_app.domain.entities.Enrollment
import com.example.pfe_att_app.domain.entities.Seance
import com.example.pfe_att_app.domain.entities.Student

data class EnrollementWithSeance(



    @Embedded val enrollment: Enrollment,
    @Relation(
        parentColumn = "seance_id",
        entityColumn = "id"
    )
    val seance: Seance

)
