package com.example.pfe_att_app.domain.use_cases.schedule

import com.example.pfe_att_app.domain.entities.Sceance
import com.example.pfe_att_app.domain.repositories.IScheduleRepository
import java.time.LocalDate
import java.util.Date


class GetSchedulesUseCase(var scheduleRepository: IScheduleRepository) {

     suspend fun execute(selectedDate: LocalDate):List<Sceance>{
      return   scheduleRepository.getSchedule(selectedDate)
    }

}