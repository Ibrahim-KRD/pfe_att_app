package com.example.pfe_att_app.domain.repositories

import com.example.pfe_att_app.domain.entities.Student

interface IStudentRepository {
    fun GetStudentsWithClass(classId : Int):List<Student>
    fun AddStudent(student: Student)
    fun DeleteStudent(student: Student)
}