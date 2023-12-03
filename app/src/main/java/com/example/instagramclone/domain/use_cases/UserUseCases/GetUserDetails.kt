package com.example.instagramclone.domain.use_cases.UserUseCases

import com.example.instagramclone.domain.repository.UserRepository
import javax.inject.Inject

class GetUserDetails @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(userid:String) =
        repository.getUserDetails(userid = userid)
}