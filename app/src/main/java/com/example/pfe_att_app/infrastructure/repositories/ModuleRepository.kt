package com.example.pfe_att_app.infrastructure.repositories



import androidx.lifecycle.LiveData
import com.example.pfe_att_app.database.ContactDao
import com.example.pfe_att_app.database.ModuleDao
import com.example.pfe_att_app.domain.entities.Module
import com.example.pfe_att_app.domain.repositories.IModuleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ModuleRepository @Inject constructor(private val moduleDao: ModuleDao): IModuleRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    override fun AddModule(module: Module) {
        coroutineScope.launch(Dispatchers.IO) {
            moduleDao.Insert(module)
        }
    }

    override fun DeleteModule(module: Module) {

    }

    override fun getAllModules():LiveData< List<Module>> {

        return moduleDao.getAllModule()
    }



}