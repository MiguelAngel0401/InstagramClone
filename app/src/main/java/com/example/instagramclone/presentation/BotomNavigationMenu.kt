package com.example.instagramclone.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.instagramclone.util.Screens
import org.checkerframework.common.subtyping.qual.Bottom

enum class BotomNavigationItem(val icon: ImageVector, val route : Screens){
    FEED(Icons.Default.Home,Screens.FeedsScreen),
    SEARCH(Icons.Default.Search,Screen. SearchScreen ),
    PROFILE(Icons.Default.Person, Screen.ProfileScreen)
}
@Composable
fun  BotomNavigationMenu(
    selectedItem : BotomNavigationItem,navController: NavController
){
    Row(modifier = Modifier.fillMaxWidth().wrapContentHeight().background(Color.White)){
        for (item in BottomNavigationItem.values()){
            Image(
                imageVector = item.icon,
                contentDescription = "ImageItem",
                modifier=Modifier.size(40.dp)
                    .weight(1f)
                    .padding(5.dp)
                    .clickable {
                        navController.navigate(item.route.route)
                    },
                colorFilter = if (item==selectedItem) ColorFilter.tint(Color.Black)
                else ColorFilter.tint(Color.Gray)
            )
        }
    }
}