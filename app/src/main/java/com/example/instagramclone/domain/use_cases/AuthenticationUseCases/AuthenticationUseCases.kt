package com.example.instagramclone.domain.use_cases.AuthenticationUseCases

data class AuthenticationUseCases (
    val isUserAuthenticated: IsUserAuthenticated,
    val firebaseAuthState: FirebaseAuthState,


    val firebaseSignIn: FirebaseSignIn,
    val firebaseSignOut: FirebaseSignOut,
    val firebaseSignUp: FirebaseSignUp,
)