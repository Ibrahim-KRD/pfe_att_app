package com.example.pfe_att_app.domain.use_cases.sceance

import com.example.pfe_att_app.domain.repositories.ISceanceRepository

class GenerateQR_CodeUseCase(var sceanceRepository: ISceanceRepository) {
    suspend fun execute(id: String): String {
        return "67"
    }

}