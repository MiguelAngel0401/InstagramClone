package com.example.instagramclone.domain.use_cases.UserUseCases

import com.example.instagramclone.domain.repository.UserRepository
import javax.inject.Inject

class SetUserDetails @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(
        userid: String,
        name: String,
        userName: String,
        bio: String,
        websiteUrl: String
    ) {
        repository.setUserDetails(
            userid = userid,
            name = name,
            username = userName,
            bio = bio,
            websiteUrl = websiteUrl
        )
    }
}
