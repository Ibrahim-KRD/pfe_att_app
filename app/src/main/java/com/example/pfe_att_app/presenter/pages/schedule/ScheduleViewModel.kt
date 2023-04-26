package com.example.pfe_att_app.presenter.pages.schedule

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.pfe_att_app.domain.entities.Sceance
import com.example.pfe_att_app.domain.entities.Student
import com.example.pfe_att_app.domain.use_cases.schedule.AddSceanceToSchedule

import com.example.pfe_att_app.infrastructure.repositories.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(private val scheduleRepository: ScheduleRepository) :
    ViewModel() {


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


    val sciences = scheduleRepository.getSchedule(LocalDate.now()).toMutableStateList()

    fun AddSceanceToSchedule(sceance: Sceance) {
        sciences.add(sceance)
    }

fun getStudentOf():MutableList<Student>{
 return   scheduleRepository.getStudentsOfSeance(sciences.get(1)).toMutableStateList()
}

}