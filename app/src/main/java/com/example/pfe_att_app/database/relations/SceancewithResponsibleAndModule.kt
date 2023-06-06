package com.example.pfe_att_app.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pfe_att_app.domain.entities.Module
import com.example.pfe_att_app.domain.entities.Seance
import com.example.pfe_att_app.domain.entities.Teacher

data class SceancewithResponsibleAndModule (


    @Embedded val sceance: Seance,
    @Relation(
            parentColumn = "responsible_id",
            entityColumn = "id"
        )
        val responsible: Teacher,
    @Relation(
            parentColumn = "module_id",
            entityColumn = "id"
        )
        val module: Module
    )

