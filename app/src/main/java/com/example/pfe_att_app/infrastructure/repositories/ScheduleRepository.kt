package com.example.pfe_att_app.infrastructure.repositories


import androidx.lifecycle.LiveData
import com.example.pfe_att_app.database.EnrollementDao
import com.example.pfe_att_app.database.SeanceDao
import com.example.pfe_att_app.database.relations.EnrollmentWithSeanceStudentModule
import com.example.pfe_att_app.database.relations.EnrollmentWithStudent
import com.example.pfe_att_app.database.relations.SceancewithResponsibleAndModule
import com.example.pfe_att_app.domain.entities.Enrollment
import com.example.pfe_att_app.domain.entities.Seance
import com.example.pfe_att_app.domain.entities.Student
import com.example.pfe_att_app.domain.repositories.IScheduleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject


class ScheduleRepository @Inject constructor(
                                             private val seanceDao: SeanceDao,
                                             private val enrollementDao: EnrollementDao
                                             ) : IScheduleRepository {


    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    override  fun AddToSchedule(sceance: Seance) {
        coroutineScope.launch(Dispatchers.IO) {
            seanceDao.Insert(sceance)
            var enrollment = Enrollment(student_id = 2, seance_id = 1, presenceState = true, mark = 10)
            enrollementDao.Insert(enrollment)

        }
    }

    override  fun DeleteFromSchedule(sceance: Seance) {

    }

    override  fun getSchedule(date: LocalDate): LiveData<List<SceancewithResponsibleAndModule>> {
       return seanceDao.getSeanceWithResponsibleAndModule()

    }

    override fun getStudentsOfSeance(sceance_id:Int): LiveData<List<EnrollmentWithStudent>> {
      return  enrollementDao.getEnrollmentsOfClass()

    }

    override fun getSeance(id: Int): LiveData<Seance> {
       return seanceDao.getSeanceById(id)
    }



   override  fun getEnrolmmentWithModule_seance_student(student_id :Int , seance_id: Int):LiveData<EnrollmentWithSeanceStudentModule?>{
         return enrollementDao.getEnrollmentWithSeanceStudent(student_id,seance_id)
    }


}