package com.example.pfe_att_app.domain.entities


class Sceance(
    var id: String,
   var responsible: Teacher,
  var  module: Module,
   var classType: String,
   var startTime: String,
    var endTime: String,
   var group: String,
   var classroom: String,
   var description: String,
    // TODO:   enrollment: List<Enrollment>
)