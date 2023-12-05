package com.example.instagramclone.domain.repository

import com.example.instagramclone.domain.model.Post
import com.example.instagramclone.util.Response
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getAllPosts(userid : String): Flow<Response<List<Post>>>

    fun uploadsPost(
        postImage : String,
        postDescription : String,
        time : Long,
        userId : String,
        userName : String,
        userImage : String,
    ) : Flow<Response<Boolean>>
}