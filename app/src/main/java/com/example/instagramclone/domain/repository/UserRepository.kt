package com.example.instagramclone.domain.repository

import com.example.instagramclone.domain.model.User
import com.example.instagramclone.util.Response
import kotlinx.coroutines.flow.Flow

interface UserRepository {
     fun getUserDetails(userid:String): Flow<Response<User>>
     fun setUserDetails(
          userid:String,
          name:String,
          username:String,
          bio:String,
          websiteUrl:String
     ): Flow<Response<Boolean>>
}