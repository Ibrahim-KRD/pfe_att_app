package com.example.pfe_att_app.domain.repositories

import androidx.lifecycle.LiveData
import com.example.pfe_att_app.domain.entities.Module

interface IModuleRepository {
     fun AddModule(module: Module)
     fun DeleteModule(module: Module)
     fun getAllModules(): LiveData<List<Module>>
}