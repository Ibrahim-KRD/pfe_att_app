package com.example.pfe_att_app.infrastructure.repositories


import com.example.pfe_att_app.domain.entities.Module
import com.example.pfe_att_app.domain.entities.Sceance
import com.example.pfe_att_app.domain.entities.Student
import com.example.pfe_att_app.domain.entities.Teacher
import com.example.pfe_att_app.domain.repositories.IScheduleRepository
import java.time.LocalDate
import javax.inject.Inject


class ScheduleRepository @Inject constructor() : IScheduleRepository {

        var sceances = GenerateDummyData()

    override  fun AddToSchedule(sceance: Sceance) {
       sceances.add(sceance)
    }

    override  fun DeleteFromSchedule(sceance: Sceance) {
       sceances.remove(sceance)
    }

    override  fun getSchedule(date: LocalDate): MutableList<Sceance> {
        return sceances
    }

    override fun getStudentsOfSeance(sceance: Sceance): List<Student> {
        val students = listOf(
            Student("John", "Doe", "123 Main St", "2022", "123456", "Bachelor", 1, "Computer Science"),
            Student("Jane", "Doe", "456 Elm St", "2021", "654321", "Bachelor", 2, "Mathematics"),
            Student("Bob", "Smith", "789 Oak Ave", "2022", "111111", "Master", 1, "Chemistry"),
            Student("Jane", "Doe", "456 Elm St", "2021", "654321", "Bachelor", 2, "Mathematics"),
            Student("Bob", "Smith", "789 Oak Ave", "2022", "111111", "Master", 1, "Chemistry"),
            Student("Jane", "Doe", "456 Elm St", "2021", "654321", "Bachelor", 2, "Mathematics"),
            Student("Bob", "Smith", "789 Oak Ave", "2022", "111111", "Master", 1, "Chemistry"),
            Student("Jane", "Doe", "456 Elm St", "2021", "654321", "Bachelor", 2, "Mathematics"),
            Student("Bob", "Smith", "789 Oak Ave", "2022", "111111", "Master", 1, "Chemistry"),
            Student("Jane", "Doe", "456 Elm St", "2021", "654321", "Bachelor", 2, "Mathematics"),
            Student("Bob", "Smith", "789 Oak Ave", "2022", "111111", "Master", 1, "Chemistry"),
            Student("Jane", "Doe", "456 Elm St", "2021", "654321", "Bachelor", 2, "Mathematics"),
            Student("Bob", "Smith", "789 Oak Ave", "2022", "111111", "Master", 1, "Chemistry"),
            Student("Jane", "Doe", "456 Elm St", "2021", "654321", "Bachelor", 2, "Mathematics"),
            Student("Bob", "Smith", "789 Oak Ave", "2022", "111111", "Master", 1, "Chemistry"),
            Student("Jane", "Doe", "456 Elm St", "2021", "654321", "Bachelor", 2, "Mathematics"),
            Student("Bob", "Smith", "789 Oak Ave", "2022", "111111", "Master", 1, "Chemistry"),
            Student("Jane", "Doe", "456 Elm St", "2021", "654321", "Bachelor", 2, "Mathematics"),
            Student("Bob", "Smith", "789 Oak Ave", "2022", "111111", "Master", 1, "Chemistry"),
            Student("Jane", "Doe", "456 Elm St", "2021", "654321", "Bachelor", 2, "Mathematics"),
            Student("Bob", "Smith", "789 Oak Ave", "2022", "111111", "Master", 1, "Chemistry"),
            Student("Jane", "Doe", "456 Elm St", "2021", "654321", "Bachelor", 2, "Mathematics"),
            Student("Bob", "Smith", "789 Oak Ave", "2022", "111111", "Master", 1, "Chemistry"),
            Student("Jane", "Doe", "456 Elm St", "2021", "654321", "Bachelor", 2, "Mathematics"),
            Student("Bob", "Smith", "789 Oak Ave", "2022", "111111", "Master", 1, "Chemistry"),
            Student("Jane", "Doe", "456 Elm St", "2021", "654321", "Bachelor", 2, "Mathematics"),
            Student("Bob", "Smith", "789 Oak Ave", "2022", "111111", "Master", 1, "Chemistry"),
            Student("Jane", "Doe", "456 Elm St", "2021", "654321", "Bachelor", 2, "Mathematics"),
            Student("Bob", "Smith", "789 Oak Ave", "2022", "111111", "Master", 1, "Chemistry")

        )
        return students
    }


    fun GenerateDummyData(): MutableList<Sceance> {

        val teacherList = listOf(
            Teacher("John", "Doe", "123 Main St", "2022", "Mathematics", "Professor"),
            Teacher("Jane", "Doe", "456 Elm St", "2020", "Computer Science", "Associate Professor"),
            Teacher("Bob", "Smith", "789 Oak St", "2023", "History", "Assistant Professor"),
            Teacher("Alice", "Johnson", "321 Pine St", "2021", "English", "Professor"),
            Teacher("David", "Brown", "654 Maple St", "2024", "Psychology", "Associate Professor")
        )

        val moduleList = listOf(
            Module("Mathematics", "This is a math course", "Advanced", "Mathematics"),
            Module("Physics", "This is a physics course", "Beginner", "Physics"),
            Module("Chemistry", "This is a chemistry course", "Intermediate", "Chemistry"),
            Module(
                "Computer Science",
                "This is a computer science course",
                "Advanced",
                "Computer Science"
            )
        )

        val groupList = listOf("A", "B", "C")

        val classroomList = listOf("101", "102", "103")

        val descriptionList = listOf(
            "Introduction to",
            "Advanced techniques in",
            "Practical applications of"
        )

        val sceances = mutableListOf<Sceance>()

        for (i in 1..10) {
            val id = i.toString()
            val responsible = teacherList.random()
            val module = moduleList.random()
            val classType = "Lecture"
            val group = groupList.random()
            val classroom = classroomList.random()
            val description = descriptionList.random() + " " + module.name


            val sceance = Sceance(id, responsible, module, classType, group, classroom, description)
            sceances.add(sceance)
        }
        return sceances
    }


}