package com.example.pfe_att_app.domain.repositories

import com.example.pfe_att_app.domain.entities.Sceance
import com.example.pfe_att_app.domain.entities.Student
import java.time.LocalDate
import java.util.Date

public interface IScheduleRepository {
     fun AddToSchedule(sceance: Sceance)
     fun DeleteFromSchedule(sceance: Sceance)
     fun getSchedule(date: LocalDate):List<Sceance>
     fun getStudentsOfSeance(sceance: Sceance):List<Student>
// TODO:     EditOnSchedule(sceance: Sceance)
}