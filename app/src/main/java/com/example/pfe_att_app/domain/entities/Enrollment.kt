package com.example.pfe_att_app.domain.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "enrollments")
class Enrollment(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var student_id: Int,
    var seance_id: Int,
    var presenceState: Boolean,
    var mark: Int
// TODO: add the state update time
)