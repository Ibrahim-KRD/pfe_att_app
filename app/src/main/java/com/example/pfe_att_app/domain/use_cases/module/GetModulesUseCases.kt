package com.example.pfe_att_app.domain.use_cases.module

import androidx.lifecycle.LiveData
import com.example.pfe_att_app.domain.entities.Module
import com.example.pfe_att_app.domain.repositories.IModuleRepository

class GetModulesUseCases(var moduleRepository: IModuleRepository) {
     fun execute(): LiveData<List<Module>> {
        return moduleRepository.getAllModules()
    }
}