package com.example.pfe_att_app.dependency_injection

import android.app.Application
import com.example.pfe_att_app.database.AttendanceApplicationDatabase
import com.example.pfe_att_app.database.ContactDao
import com.example.pfe_att_app.database.EnrollementDao
import com.example.pfe_att_app.database.ModuleDao
import com.example.pfe_att_app.database.SeanceDao
import com.example.pfe_att_app.database.StudentDao
import com.example.pfe_att_app.database.TeacherDao
import com.example.pfe_att_app.domain.repositories.IAuthenticationRepository
import com.example.pfe_att_app.domain.repositories.IModuleRepository
import com.example.pfe_att_app.domain.repositories.IScheduleRepository
import com.example.pfe_att_app.infrastructure.repositories.AuthenticationRepository
import com.example.pfe_att_app.infrastructure.repositories.ModuleRepository
import com.example.pfe_att_app.infrastructure.repositories.ScheduleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun ProvideModuleRepository(moduleDao: ModuleDao):IModuleRepository{
        return ModuleRepository(moduleDao)
    }

    @Provides
    @Singleton
    fun provideScheduleRepository(seanceDao: SeanceDao,enrollementDao: EnrollementDao,studentDao: StudentDao): IScheduleRepository {
        return ScheduleRepository(seanceDao,enrollementDao,studentDao)
    }
    @Provides
    @Singleton
    fun provideAttendanceApplicationDatabase(context: Application): AttendanceApplicationDatabase {
        return AttendanceApplicationDatabase.getInstance(context)
    }
    @Provides
    @Singleton
    fun provideContactDao(database: AttendanceApplicationDatabase): ContactDao {
        return database.contactDao()
    }

    @Provides
    @Singleton
    fun provideModuleDao(database: AttendanceApplicationDatabase): ModuleDao {
        return database.moduleDao()
    }

    @Provides
    @Singleton
    fun provideTeacherDao(database: AttendanceApplicationDatabase): TeacherDao {
        return database.teacherDao()
    }


    @Provides
    @Singleton
    fun provideStudentDao(database: AttendanceApplicationDatabase): StudentDao {
        return database.studentDao()
    }

    @Provides
    @Singleton
    fun provideEnrollmentDao(database: AttendanceApplicationDatabase): EnrollementDao {
        return database.enrollmentDao()
    }


    @Provides
    @Singleton
    fun provideSeanceDao(database: AttendanceApplicationDatabase): SeanceDao {
        return database.seanceDao()
    }



    @Provides
    @Singleton
    fun provideAuthenticationRepository(teacherDao: TeacherDao,studentDao: StudentDao): IAuthenticationRepository {
        return AuthenticationRepository(teacherDao,studentDao)
    }



}