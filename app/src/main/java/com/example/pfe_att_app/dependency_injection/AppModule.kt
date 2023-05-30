package com.example.pfe_att_app.dependency_injection

import com.example.pfe_att_app.Repository
import com.example.pfe_att_app.domain.IRepository
import com.example.pfe_att_app.domain.repositories.IAuthenticationRepository
import com.example.pfe_att_app.domain.repositories.IModuleRepository
import com.example.pfe_att_app.domain.repositories.IScheduleRepository
import com.example.pfe_att_app.infrastructure.repositories.AuthenticationRepository
import com.example.pfe_att_app.infrastructure.repositories.ModuleRepository
import com.example.pfe_att_app.infrastructure.repositories.ScheduleRepository
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
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
    fun ProvideModuleRepository():IModuleRepository{
        return ModuleRepository()
    }

    @Provides
    @Singleton
    fun provideScheduleRepository(): IScheduleRepository {
        return ScheduleRepository()
    }

    @Provides
    @Singleton
    fun provideAuthenticationRepository(impl:AuthenticationRepository): IAuthenticationRepository = impl

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
}