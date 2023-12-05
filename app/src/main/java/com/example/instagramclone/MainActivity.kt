package com.example.instagramclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instagramclone.presentation.Authentication.AuthenticationViewModel
import com.example.instagramclone.presentation.Authentication.LoginScreen
import com.example.instagramclone.presentation.Authentication.SignUpScreen
import com.example.instagramclone.presentation.Main.FeedsScreen
import com.example.instagramclone.presentation.Main.Profile.ProfileScreen
import com.example.instagramclone.presentation.Main.SearchScreen
import com.example.instagramclone.presentation.SplashScreen
import com.example.instagramclone.ui.theme.InstagramCloneTheme
import com.example.instagramclone.util.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramCloneTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val authViewModel: AuthenticationViewModel = hiltViewModel()
                    InstagramCloneApp(navController, authenticationViewModel = authViewModel)
                }
            }
        }
    }
}

@Composable
fun InstagramCloneApp(navController: NavController, authenticationViewModel: AuthenticationViewModel) {
    NavHost(navController = navController as NavHostController, startDestination = Screens.SplashScreen.route) {
        composable(route = Screens.LoginScreen.route) {
            LoginScreen(navController = navController, viewModel = authenticationViewModel)
        }
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navController, authenticationViewModel)
        }
        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController = navController, authViewModel = authenticationViewModel)
        }
        composable(route = Screens.FeedsScreen.route) {
            FeedsScreen(navController = navController)
        }
        composable(route = Screens.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
        composable(route = Screens.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
    }
}





