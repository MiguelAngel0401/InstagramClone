package com.example.instagramclone.presentation.Main

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.instagramclone.presentation.BottomNavigationItem
import com.example.instagramclone.presentation.BottomNavigationMenu
import com.example.instagramclone.presentation.Main.Posts.PostViewModel
import com.example.instagramclone.presentation.Toast
import com.example.instagramclone.util.Response
import com.example.instagramclone.R
import com.example.instagramclone.domain.model.Post

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedsScreen(navController:NavController){
    val postViewModel : PostViewModel = hiltViewModel()
    postViewModel.getAllPosts()
    when(val response =  postViewModel.postData.value){
        is Response.Loading->{
            CircularProgressIndicator()
        }
        is Response.Success-> {
            val obj = response.data
            Scaffold(topBar = {
                TopAppBar(title = { Text(text = "Instagram",) },
                    backgroundColor = MaterialTheme.colors.surface,
                    elevation = 10.dp,
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(painter = painterResource(id = R.drawable.ic_instagram), contentDescription = "Logo")
                          }
                       },
                    actions ={
                        IconButton(onClick = { }) {
                            Icon(painter = painterResource(id = R.drawable.ic_message), contentDescription = "Message" ,Modifier.size(30.dp), tint= Color.Black)
                        }
                    }
                )
                 },content = {
                     InstagramHomeContent()
                 },
                bottomBar = {
                    BottomNavigationMenu(selectedItem = BottomNavigationItem.FEED, navController= navController)
                }
            )
        }

        is Response.Error->{
            Toast(message = response.message)
        }
    }
}

@Composable
fun InstagramHomeContent(postsList:List<Post>) {
    LazyColumn{
        items(postsList, itemContent = {

        })
    }
}

