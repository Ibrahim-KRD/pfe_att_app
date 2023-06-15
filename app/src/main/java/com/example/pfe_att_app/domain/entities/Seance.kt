package com.example.pfe_att_app.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Seance(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var responsible_id: Int,
    var module_id: Int,
    var classType: String,
    var startTime: String,
    var endTime: String,
    var level: String,
    var group: String,
    var classroom: String,
    var description: String,
    var day: String


)