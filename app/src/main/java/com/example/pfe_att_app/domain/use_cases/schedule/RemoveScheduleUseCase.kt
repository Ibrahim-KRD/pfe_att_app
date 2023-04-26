package com.example.pfe_att_app.domain.use_cases.schedule

import com.example.pfe_att_app.domain.entities.Sceance
import com.example.pfe_att_app.domain.repositories.IScheduleRepository

class RemoveScheduleUseCase( var scheduleRepository: IScheduleRepository) {
    suspend fun execute(sceance: Sceance){
        scheduleRepository.DeleteFromSchedule(sceance)
    }

}