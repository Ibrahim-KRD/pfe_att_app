package com.example.pfe_att_app.domain.use_cases.sceance

import com.example.pfe_att_app.domain.entities.Sceance
import com.example.pfe_att_app.domain.repositories.ISceanceRepository
import com.example.pfe_att_app.domain.repositories.IScheduleRepository

class getSceanceUseCase(var sceanceRepository: ISceanceRepository) {

    suspend fun execute(id: String): Sceance {
        return sceanceRepository.getSceance(id)
    }
}