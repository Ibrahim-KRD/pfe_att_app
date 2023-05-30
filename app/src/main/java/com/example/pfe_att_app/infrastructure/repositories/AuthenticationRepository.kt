package com.example.pfe_att_app.infrastructure.repositories

import com.example.pfe_att_app.data.Resource
import com.example.pfe_att_app.data.await
import com.example.pfe_att_app.domain.entities.Person
import com.example.pfe_att_app.domain.repositories.IAuthenticationRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : IAuthenticationRepository {

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun LogIn(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun Register(
        email: String,
        password: String,
        name: String
    ): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result?.user?.updateProfile(
                UserProfileChangeRequest.Builder().setDisplayName(name).build()
            )
            Resource.Success(result.user!!)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Resource.Failure(e)

        }

    }

    override fun LogOut() {
        firebaseAuth.signOut()
    }

}