package com.example.pfe_att_app

import com.example.pfe_att_app.domain.IRepository

class Repository : IRepository {

    override fun DoAction(name:String): String {
      return "Hello $name"
    }
}