package com.example.instagramclone.presentation.Main

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.instagramclone.presentation.Profile.UserViewModel
import com.example.instagramclone.util.Response

@Composable
fun ProfileScreen(navController:NavController){
    val userViewModel : UserViewModel = hiltViewModel()
    userViewModel.getUserInfo()
    when(val response=userViewModel.getUserData.value){
        is Response.Loading->{

        }
        is Response.Error->{

        }
        is Response.Success->{
            Log.d("successful", response.data.toString())
        }
    }
Column (modifier = Modifier.fillMaxSize()){
    Column(modifier=Modifier.weight(1f)) {
        Text(text = "Profile Screen")
    }
    BottomNavigationMenu(selectedItem = BottomNavigationItem.PROFILE, navController= navController)
  }
}
