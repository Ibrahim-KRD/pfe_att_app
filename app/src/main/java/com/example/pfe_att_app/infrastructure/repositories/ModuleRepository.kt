package com.example.pfe_att_app.infrastructure.repositories



import com.example.pfe_att_app.domain.entities.Module
import com.example.pfe_att_app.domain.repositories.IModuleRepository

class ModuleRepository : IModuleRepository {

    var modules : MutableList<Module> = mutableListOf(
        Module("Algorithm", "data structor and stuff like that", "License 1", "Informatic"),
        Module("Database Systems", "database design and management", "License 2", "Informatic"),
        Module("Web Development", "front-end and back-end web development", "License 1", "Informatic"),
        Module("Artificial Intelligence", "machine learning and deep learning", "License 2", "Informatic"),
        Module("Mobile Application Development", "Android and iOS app development", "License 1", "Informatic"),
        Module("Network Security", "network design and security", "License 2", "Informatic"),
        Module("Algorithm", "data structor and stuff like that", "License 1", "Informatic"),
        Module("Database Systems", "database design and management", "License 2", "Informatic"),
        Module("Web Development", "front-end and back-end web development", "License 1", "Informatic"),
        Module("Artificial Intelligence", "machine learning and deep learning", "License 2", "Informatic"),
        Module("Mobile Application Development", "Android and iOS app development", "License 1", "Informatic"),
        Module("Network Security", "network design and security", "License 2", "Informatic")

      )




    override fun AddModule(module: Module) {
       modules.add(module)
    }

    override fun DeleteModule(module: Module) {
        modules.remove(module)
    }

    override fun getAllModules(): List<Module> {

        return modules
    }



}