package com.example.pfe_att_app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.pfe_att_app.database.relations.EnrollmentWithSeanceStudentModule
import com.example.pfe_att_app.database.relations.EnrollmentWithStudent
import com.example.pfe_att_app.domain.entities.Enrollment
import com.example.pfe_att_app.domain.entities.Seance

@Dao
interface EnrollementDao {

    @Transaction
    @Query("SELECT * FROM enrollments where seance_id = :sceance_id")
    fun getEnrollmentsOfClass(sceance_id: Int): LiveData<List<EnrollmentWithStudent>>

    @Insert
    suspend fun Insert(enrollment: Enrollment)


    @Transaction
    @Insert
    fun insertSeanceWithEnrollments(seance: Seance, enrollments: List<Enrollment>)



    @Transaction
    @Query("SELECT enrollments.*, Seance.*, Student.* FROM enrollments " +
            "INNER JOIN Seance ON enrollments.seance_id = Seance.id " +
            "INNER JOIN Student ON enrollments.student_id = Student.id " +
            "WHERE enrollments.student_id = :studentId AND enrollments.seance_id = :seanceId")
    fun getEnrollmentWithSeanceStudent(studentId: Int, seanceId: Int): EnrollmentWithSeanceStudentModule?

}