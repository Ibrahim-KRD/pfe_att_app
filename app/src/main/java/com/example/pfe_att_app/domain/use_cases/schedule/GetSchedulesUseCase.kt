package com.example.pfe_att_app.domain.use_cases.schedule

import androidx.lifecycle.LiveData
import com.example.pfe_att_app.database.relations.SceancewithResponsibleAndModule
import com.example.pfe_att_app.domain.repositories.IScheduleRepository
import java.time.LocalDate


class GetSchedulesUseCase(var scheduleRepository: IScheduleRepository) {

     suspend fun execute(selectedDate: LocalDate):LiveData<List<SceancewithResponsibleAndModule>>{
      return   scheduleRepository.getSchedule(selectedDate)
    }

}