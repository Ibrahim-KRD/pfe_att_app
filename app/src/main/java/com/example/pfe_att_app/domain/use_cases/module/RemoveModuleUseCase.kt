package com.example.pfe_att_app.domain.use_cases.module

import com.example.pfe_att_app.domain.entities.Module
import com.example.pfe_att_app.domain.repositories.IModuleRepository

class RemoveModuleUseCase(var moduleRepository: IModuleRepository) {

 fun execute(module: Module){
    moduleRepository.DeleteModule(module)
}
}