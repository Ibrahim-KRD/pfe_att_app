package com.example.pfe_att_app.domain.repositories

import androidx.lifecycle.LiveData
import com.example.pfe_att_app.database.relations.EnrollmentWithSeanceStudentModule
import com.example.pfe_att_app.database.relations.EnrollmentWithStudent
import com.example.pfe_att_app.database.relations.SceancewithResponsibleAndModule
import com.example.pfe_att_app.database.relations.SeanceWithModule
import com.example.pfe_att_app.domain.entities.Seance
import com.example.pfe_att_app.domain.entities.Student
import java.time.LocalDate

public interface IScheduleRepository {
     fun AddToSchedule(sceance: Seance,students:List<Student>)
     fun DeleteFromSchedule(sceance: Seance)
     fun getSchedule(date: LocalDate):LiveData<List<SceancewithResponsibleAndModule>>
     fun getStuedntScheduleof(date: LocalDate,studentId:Int):LiveData<List<SceancewithResponsibleAndModule>>
     fun getStudentsOfSeance(sceance_id: Int):LiveData<List<EnrollmentWithStudent>>
     fun getSeance(id:Int):LiveData<Seance>

     fun getEnrolmmentWithModule_seance_student(enrollement_id: Int, seance_id: Int): EnrollmentWithSeanceStudentModule?

     fun getStudent(): List<Student?>?

     fun getSceaneWithModule(eance_id:Int): SeanceWithModule?

     fun getLastSeanceID():Int?

     fun getScheduleForTeacher(date: LocalDate):List<SceancewithResponsibleAndModule?>?

}