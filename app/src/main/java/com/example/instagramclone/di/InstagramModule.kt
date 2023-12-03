package com.example.instagramclone.di

import com.example.instagramclone.data.AuthenticationRepositoryImpl
import com.example.instagramclone.data.UserRepositoryImpl
import com.example.instagramclone.domain.repository.AuthenticationRepository
import com.example.instagramclone.domain.repository.UserRepository
import com.example.instagramclone.domain.use_cases.AuthenticationUseCases.AuthenticationUseCases
import com.example.instagramclone.domain.use_cases.AuthenticationUseCases.FirebaseAuthState
import com.example.instagramclone.domain.use_cases.AuthenticationUseCases.FirebaseSignIn
import com.example.instagramclone.domain.use_cases.AuthenticationUseCases.FirebaseSignOut
import com.example.instagramclone.domain.use_cases.AuthenticationUseCases.FirebaseSignUp
import com.example.instagramclone.domain.use_cases.AuthenticationUseCases.IsUserAuthenticated
import com.example.instagramclone.domain.use_cases.UserUseCases.GetUserDetails
import com.example.instagramclone.domain.use_cases.UserUseCases.SetUserDetails
import com.example.instagramclone.domain.use_cases.UserUseCases.UserUseCases
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object InstagramModule {

    @Singleton
    @Provides
    fun provideFirebaseAuthentication():FirebaseAuth{
        return FirebaseAuth.getInstance()
    }
    @Singleton
    @Provides
    fun provideFirebaseFirestore():FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }
    @Singleton
    @Provides
    fun provideFirebaseStorage():FirebaseStorage{
        return FirebaseStorage.getInstance()
    }
    @Singleton
    @Provides
    fun provideAuthenticationRepository(auth:FirebaseAuth,firestore:FirebaseFirestore):AuthenticationRepository{
        return AuthenticationRepositoryImpl(auth = auth , firestore = firestore)
    }

    @Singleton
    @Provides
    fun provideAuthUseCases(repository: AuthenticationRepositoryImpl) = AuthenticationUseCases(
        isUserAuthenticated = IsUserAuthenticated(repository = repository),
        firebaseAuthState = FirebaseAuthState(repository = repository),
        firebaseSignOut = FirebaseSignOut(repository = repository),
        firebaseSignIn = FirebaseSignIn(repository = repository),
        firebaseSignUp = FirebaseSignUp(repository = repository)
    )
    @Singleton
    @Provides
    fun providesUserRepository(firebaseFirestore: FirebaseFirestore):UserRepository{
        return UserRepositoryImpl(firebaseFirestore = firebaseFirestore)
    }
    @Singleton
    @Provides
    fun providesUserUseCases(repository: UserRepository)=UserUseCases(
        getUserDetails = GetUserDetails(repository = repository),
        setUserDetails = SetUserDetails(repository = repository)
    )
}