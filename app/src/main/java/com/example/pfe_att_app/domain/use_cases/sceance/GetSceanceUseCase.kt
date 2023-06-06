package com.example.pfe_att_app.domain.use_cases.sceance

import com.example.pfe_att_app.domain.entities.Seance
import com.example.pfe_att_app.domain.repositories.ISceanceRepository

class getSceanceUseCase(var sceanceRepository: ISceanceRepository) {

    suspend fun execute(id: String): Seance {
        return sceanceRepository.getSceance(id)
    }
}