package com.example.pfe_att_app.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pfe_att_app.domain.entities.Seance
import com.example.pfe_att_app.domain.entities.Teacher

class SeanceWithResponsible (

    @Embedded val teacher: Teacher,
    @Relation(
        parentColumn = "id",
        entityColumn = "responsible_id"
    )
    val seances: List<Seance>

)