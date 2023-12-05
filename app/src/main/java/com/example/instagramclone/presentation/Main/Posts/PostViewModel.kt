package com.example.instagramclone.presentation.Main.Posts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramclone.domain.model.Post
import com.example.instagramclone.domain.use_cases.PostUseCases.PostsUseCases
import com.example.instagramclone.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostViewModel @Inject constructor(
    private val postsUseCases: PostsUseCases
) : ViewModel()
{
    private val _postData = mutableStateOf<Response<List<Post>>>(Response.Loading)
    val postData : State<Response<List<Post>>> = _postData

    private val _uploadPostData = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val uploadPostData : State<Response<Boolean>> = _uploadPostData

    fun getAllPosts(userid:String){
        viewModelScope.launch {
            postsUseCases.getAllPosts(userid).collect{
                _postData.value = it
            }
        }
    }
    fun uploadPost(
        postImage : String,
        postDescription : String,
        time:Long,
        userid: String,
        userName:String,
        userImage:String
    ) {
        fun uploadPost() {
            viewModelScope.launch {
                postsUseCases.uploadPost(
                    userName = userName,
                    userImage = userImage,
                    userid = userid,
                    postImage = postImage,
                    postDescription = postDescription,
                    time = time,

                ).collect {
                    _uploadPostData.value = it
                }
            }
        }
    }
}
