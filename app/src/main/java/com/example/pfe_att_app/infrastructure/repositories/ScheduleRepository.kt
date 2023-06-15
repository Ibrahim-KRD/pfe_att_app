package com.example.pfe_att_app.infrastructure.repositories


import androidx.lifecycle.LiveData
import com.example.pfe_att_app.database.EnrollementDao
import com.example.pfe_att_app.database.SeanceDao
import com.example.pfe_att_app.database.StudentDao
import com.example.pfe_att_app.database.relations.EnrollmentWithSeanceStudentModule
import com.example.pfe_att_app.database.relations.EnrollmentWithStudent
import com.example.pfe_att_app.database.relations.SceancewithResponsibleAndModule
import com.example.pfe_att_app.database.relations.SeanceWithModule
import com.example.pfe_att_app.domain.entities.Enrollment
import com.example.pfe_att_app.domain.entities.Seance
import com.example.pfe_att_app.domain.entities.Student
import com.example.pfe_att_app.domain.repositories.IScheduleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject


class ScheduleRepository @Inject constructor(
    private val seanceDao: SeanceDao,
    private val enrollementDao: EnrollementDao,
    private val studentDao: StudentDao
) : IScheduleRepository {


    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun AddToSchedule(seance: Seance, students: List<Student>) {
        var lastId: Int = 0

        lastId = getLastSeanceID() ?: 0

        val enrollments = students.map { student ->
            Enrollment(
                student_id = student.id,
                seance_id = lastId + 1,
                presenceState = false,
                mark = 0
            )
        }

        coroutineScope.launch(Dispatchers.IO) {
            seanceDao.insertSeanceWithEnrollments(seance, enrollments)
        }
    }


    override fun DeleteFromSchedule(sceance: Seance) {

    }

    override fun getSchedule(date: LocalDate): LiveData<List<SceancewithResponsibleAndModule>> {

        return seanceDao.getSeanceWithResponsibleAndModule()

    }
    override fun getStuedntScheduleof(date: LocalDate,studentId:Int): LiveData<List<SceancewithResponsibleAndModule>> {

        return seanceDao.getSeanceWithResponsibleAndModuleForStudent(studentId)

    }

    override fun getStudentsOfSeance(sceance_id: Int): LiveData<List<EnrollmentWithStudent>> {
        return enrollementDao.getEnrollmentsOfClass(sceance_id)

    }

    override fun getSeance(id: Int): LiveData<Seance> {
        return seanceDao.getSeanceById(id)
    }


    override fun getEnrolmmentWithModule_seance_student(
        student_id: Int,
        seance_id: Int
    ): EnrollmentWithSeanceStudentModule? {
        var student_enrollmeent_seance: EnrollmentWithSeanceStudentModule?
        runBlocking {
            withContext(Dispatchers.IO) {
                student_enrollmeent_seance =
                    enrollementDao.getEnrollmentWithSeanceStudent(student_id, seance_id)
            }
        }
        return student_enrollmeent_seance
    }

    override fun getStudent(): List<Student?>? {

        var students: List<Student?>? = null
        runBlocking {
            withContext(Dispatchers.IO) {
                students = studentDao.getStudent()
            }
        }
        return students


    }

    override fun getSceaneWithModule(seance_id: Int): SeanceWithModule? {
        var seance_module_info: SeanceWithModule?
        runBlocking {
            withContext(Dispatchers.IO) {
                seance_module_info = seanceDao.getSeancesWithModule(seance_id)
            }
        }
        return seance_module_info
    }

    override fun getLastSeanceID(): Int? {
        var last_id: Int?
        runBlocking {
            withContext(Dispatchers.IO) {
                last_id = seanceDao.getLastSavedseance()
            }
        }
        return last_id
    }

    override fun getScheduleForTeacher(date: LocalDate): List<SceancewithResponsibleAndModule?>? {
        var seances :List<SceancewithResponsibleAndModule?>? = null
        runBlocking {
            withContext(Dispatchers.IO) {
                seances = seanceDao.getSeanceWithResponsibleAndModuleForTeacher(date.toString())

            }}
        return seances
    }


}