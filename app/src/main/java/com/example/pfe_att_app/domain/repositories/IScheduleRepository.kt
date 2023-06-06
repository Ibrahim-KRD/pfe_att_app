package com.example.pfe_att_app.domain.repositories

import androidx.lifecycle.LiveData
import com.example.pfe_att_app.database.relations.EnrollmentWithStudent
import com.example.pfe_att_app.database.relations.SceancewithResponsibleAndModule
import com.example.pfe_att_app.database.relations.SeanceWithResponsible
import com.example.pfe_att_app.domain.entities.Contact
import com.example.pfe_att_app.domain.entities.Seance
import com.example.pfe_att_app.domain.entities.Student
import java.time.LocalDate

public interface IScheduleRepository {
     fun AddToSchedule(sceance: Seance)
     fun DeleteFromSchedule(sceance: Seance)
     fun getSchedule(date: LocalDate):LiveData<List<SceancewithResponsibleAndModule>>
     fun getStudentsOfSeance(sceance_id: Int):LiveData<List<EnrollmentWithStudent>>
     fun getSeance(id:Int):LiveData<Seance>
// TODO:     EditOnSchedule(sceance: Sceance)
}