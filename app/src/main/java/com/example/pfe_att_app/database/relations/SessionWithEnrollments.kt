package com.example.pfe_att_app.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pfe_att_app.domain.entities.Enrollment
import com.example.pfe_att_app.domain.entities.Seance

data class SessionWithEnrollments(

    @Embedded val session: Seance,
    @Relation(
        parentColumn = "id",
        entityColumn = "seance_id"
    )
    val enrollments: List<Enrollment>

)
