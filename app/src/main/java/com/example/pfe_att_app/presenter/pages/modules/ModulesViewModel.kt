package com.example.pfe_att_app.presenter.pages.modules


import androidx.compose.runtime.mutableStateListOf

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.pfe_att_app.domain.entities.Module
import com.example.pfe_att_app.domain.entities.Sceance
import com.example.pfe_att_app.domain.entities.Teacher
import com.example.pfe_att_app.domain.repositories.IModuleRepository
import com.example.pfe_att_app.domain.use_cases.module.AddModuleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ModulesViewModel @Inject constructor(private val moduleRepository: IModuleRepository) :
    ViewModel() {


    val modules = moduleRepository.getAllModules().toMutableStateList()

    fun AddModule(module: Module) {
        modules.add(module)
    }


    fun generateSceances(): MutableList<Sceance> {


        Teacher("David", "Brown", "654 Maple St", "2024", "Psychology", "Associate Professor")

        val teacher1 = Teacher("David", "Brown", "654 Maple St", "2024", "Psychology", "Associate Professor")
        val teacher2 = Teacher("David", "Brown", "654 Maple St", "2024", "Psychology", "Associate Professor")
        val module1 = Module("Algorithm", "data structure and stuff like that", "License 1", "Informatics")
        val module2 = Module("Database", "SQL, NoSQL, and other database related topics", "License 2", "Informatics")
        val classroom1 = "C001"
        val classroom2 = "C002"
        val sceance1 = Sceance("1", teacher1, module1, "TD", "G1", classroom1, "Introduction to algorithms")
        val sceance2 = Sceance("2", teacher1, module1, "TP", "G2", classroom2, "Data structures and algorithms")
        val sceance3 = Sceance("3", teacher2, module2, "Courses", "G3", classroom1, "Introduction to database systems")
        return mutableListOf(sceance1, sceance2, sceance3)
    }


}