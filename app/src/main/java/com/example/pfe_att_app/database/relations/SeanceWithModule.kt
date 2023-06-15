package com.example.pfe_att_app.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pfe_att_app.domain.entities.Module
import com.example.pfe_att_app.domain.entities.Seance

data class SeanceWithModule(
    @Embedded val seance: Seance,
    @Relation(
        parentColumn = "module_id",
        entityColumn = "id"
    )
    val module: Module

)
