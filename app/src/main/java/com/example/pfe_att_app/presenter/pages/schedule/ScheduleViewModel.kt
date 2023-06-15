package com.example.pfe_att_app.presenter.pages.schedule

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe_att_app.database.relations.EnrollmentWithSeanceStudentModule
import com.example.pfe_att_app.database.relations.EnrollmentWithStudent
import com.example.pfe_att_app.database.relations.SceancewithResponsibleAndModule
import com.example.pfe_att_app.database.relations.SeanceWithModule
import com.example.pfe_att_app.domain.entities.Seance
import com.example.pfe_att_app.domain.entities.Student

import com.example.pfe_att_app.infrastructure.repositories.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(private val scheduleRepository: ScheduleRepository) :
    ViewModel() {


    var seances: LiveData<List<SceancewithResponsibleAndModule>> = getSchedule()


    //region day navigation
    val currentDate = mutableStateOf(LocalDate.now())
    fun incrementMonth() {
        currentDate.value = currentDate.value.plusMonths(1)
        getCurrentMonthDays(currentDate.value)
    }

    fun decrementMonth() {
        currentDate.value = currentDate.value.minusMonths(1)
        getCurrentMonthDays(currentDate.value)
    }

    fun getCurrentMonthDays(currentDay: LocalDate): List<LocalDate> {
        val daysInMonth = mutableListOf<LocalDate>()

        val firstDayOfMonth = currentDay.withDayOfMonth(1)
        val lastDayOfMonth =
            currentDay.withDayOfMonth(currentDay.month.length(currentDay.isLeapYear))


        var day = firstDayOfMonth
        while (day.isBefore(lastDayOfMonth.plusDays(1))) {
            daysInMonth.add(day)
            day = day.plusDays(1)
        }
        return daysInMonth

    }

    fun getMonthName(): String {
        val monthYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.getDefault())
        return monthYearFormatter.format(currentDate.value)

    }

    fun getFullDayInformation(): String {
        val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d uuuu", Locale.ENGLISH)


        return currentDate.value.format(formatter)
    }
//endregion


    fun AddSceanceToSchedule(sceance: Seance, students: List<Student?>?) {
        scheduleRepository.AddToSchedule(sceance, students as List<Student>)
    }

    fun getSchedule(): LiveData<List<SceancewithResponsibleAndModule>> {
        return scheduleRepository.getSchedule(LocalDate.now())
    }

    fun getStudentSchedule(studentId:Int): LiveData<List<SceancewithResponsibleAndModule>> {
        return scheduleRepository.getStuedntScheduleof(LocalDate.now(),studentId)
    }

    fun getScheduleForTeacher():List<SceancewithResponsibleAndModule?>?{
        return scheduleRepository.getScheduleForTeacher(currentDate.value)
    }

    fun getStudentOf(seance_id: Int): LiveData<List<EnrollmentWithStudent>> {
        return scheduleRepository.getStudentsOfSeance(seance_id)
    }

    fun getSeanceById(id: Int): LiveData<Seance> {
        return scheduleRepository.getSeance(id)
    }

    fun getEnrollmentWithStudentModuleSeance(student_id:Int , seance_id:Int):EnrollmentWithSeanceStudentModule?{
        return scheduleRepository.getEnrolmmentWithModule_seance_student(student_id,seance_id)
    }


    fun getStudents(): List<Student?>? {

       var students : List<Student?>? = null

        viewModelScope.launch {
            students = scheduleRepository.getStudent()
        }
        return students
    }

    fun getSeanceWithModule(seance_id: Int):SeanceWithModule?{
        return scheduleRepository.getSceaneWithModule(seance_id)
    }

}