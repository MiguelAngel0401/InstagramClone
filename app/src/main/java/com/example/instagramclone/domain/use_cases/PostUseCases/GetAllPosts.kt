package com.example.instagramclone.domain.use_cases.PostUseCases

import com.example.instagramclone.domain.repository.PostRepository
import javax.inject.Inject

class GetAllPosts @Inject constructor(
    private val repository: PostRepository
) {
    operator fun invoke(userid:String)=repository.getAllPosts(userid=userid)
}