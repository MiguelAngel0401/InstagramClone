package com.example.instagramclone.domain.repository

import com.example.instagramclone.util.Response
import kotlinx.coroutines.flow.Flow


interface AuthenticationRepository {

    fun  isUserAuthenticationFirebase():Boolean

    fun getFirebaseAuthState() : Flow<Boolean>

    fun firebaseSignIn(email:String, password:String) : Flow<Response<Boolean>>

    fun firebaseSingOut() : Flow<Response<Boolean>>

    fun firebaseSignUp(email:String, password:String, userName:String) : Flow<Response<Boolean>>
}