package com.example.pfe_att_app.domain.use_cases.schedule

import com.example.pfe_att_app.domain.entities.Seance
import com.example.pfe_att_app.domain.repositories.IScheduleRepository

class AddSceanceToSchedule( var scheduleRepository: IScheduleRepository) {
    suspend fun execute(sceance: Seance){
        scheduleRepository.AddToSchedule(sceance)
    }

}