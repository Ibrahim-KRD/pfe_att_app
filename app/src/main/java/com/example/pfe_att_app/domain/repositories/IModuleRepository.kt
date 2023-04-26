package com.example.pfe_att_app.domain.repositories

import com.example.pfe_att_app.domain.entities.Module

interface IModuleRepository {
     fun AddModule(module: Module)
     fun DeleteModule(module: Module)
     fun getAllModules(): List<Module>
}