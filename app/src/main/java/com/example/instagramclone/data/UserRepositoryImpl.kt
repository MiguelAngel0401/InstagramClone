package com.example.instagramclone.data

import androidx.compose.runtime.mutableStateOf
import com.example.instagramclone.domain.model.User
import com.example.instagramclone.domain.repository.UserRepository
import com.example.instagramclone.util.Response
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
): UserRepository {
    override fun getUserDetails(userid:String): Flow<Response<User>> = callbackFlow {
        Response.Loading
        val snapshotListener = firebaseFirestore.collection("users")
            .document(userid)
            .addSnapshotListener{snapshot,error->
                val response = if (snapshot!=null){
                    val userInfo = snapshot.toObject(User::class.java)
                    Response.Success<User>(userInfo!!)
                }
                else {
                    Response.Error(error?.message?:error.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose{
            snapshotListener.remove()
        }
    }

    override fun setUserDetails(
        userid: String,
        name: String,
        username: String,
        bio: String,
        websiteUrl: String,
    ): Flow<Response<Boolean>> = flow {
        try{
            val userObj = mutableMapOf<String,String>()
            userObj["name"] = name
            userObj["userName"] = username
            userObj["bio"] = bio
            userObj["websiteUrl"] = websiteUrl
            firebaseFirestore.collection("users").document(userid).update(userObj as Map<String, Any>)

        }
        catch (e:Exception){
            Response.Error(e.localizedMessage?:"An Unexpected Error")

        }
    }
}