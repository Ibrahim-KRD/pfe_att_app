package com.example.pfe_att_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pfe_att_app.domain.entities.Contact
import com.example.pfe_att_app.domain.entities.Enrollment
import com.example.pfe_att_app.domain.entities.Module
import com.example.pfe_att_app.domain.entities.Seance
import com.example.pfe_att_app.domain.entities.Student
import com.example.pfe_att_app.domain.entities.Teacher

@Database(
    entities = [Contact::class,Module::class,Seance::class, Teacher::class, Student::class,Enrollment::class],
    version = 1
    , exportSchema = false
)
abstract class AttendanceApplicationDatabase : RoomDatabase() {
    abstract fun contactDao():ContactDao
    abstract fun moduleDao():ModuleDao
    abstract fun seanceDao():SeanceDao
    abstract fun teacherDao():TeacherDao
    abstract fun enrollmentDao():EnrollementDao
    abstract fun studentDao():StudentDao

companion object{


    @Volatile
    private var INSTANCE : AttendanceApplicationDatabase? = null



    fun getInstance(context: Context): AttendanceApplicationDatabase {
        // only one thread of execution at a time can enter this block of code
        synchronized(this) {
            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AttendanceApplicationDatabase::class.java,
                    "attendance_application_database"
                ).fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
            }
            return instance
        }
    }


}

}