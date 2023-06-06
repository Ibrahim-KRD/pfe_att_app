package com.example.pfe_att_app.presenter.pages.modules


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pfe_att_app.domain.entities.Module
import com.example.pfe_att_app.domain.repositories.IModuleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ModulesViewModel @Inject constructor(private val moduleRepository: IModuleRepository) :
    ViewModel() {




    fun AddModule(module: Module) {
       moduleRepository.AddModule(module)
    }


   fun getModules():LiveData<List<Module>>{
       return moduleRepository.getAllModules()
   }


}