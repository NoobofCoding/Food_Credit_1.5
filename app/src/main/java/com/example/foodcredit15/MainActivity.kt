package com.example.foodcredit15

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodcredit15.network.RetrofitClient
import com.example.foodcredit15.ui.screens.UserLoginScreen
import com.example.foodcredit15.ui.screens.SplashScreen
import com.example.foodcredit15.ui.internals.AccountScreen
import com.example.foodcredit15.ui.internals.Order
import com.example.foodcredit15.ui.internals.OrderHistoryScreen
import com.example.foodcredit15.ui.internals.OrderScreen
import com.example.foodcredit15.ui.screens.RegistrationScreen
import com.example.foodcredit15.ui.internals.SettingsScreen
import com.example.foodcredit15.network.ApiService
import com.example.foodcredit15.ui.internals.DashboardScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val api = RetrofitClient.instance.create(ApiService::class.java)
                    NavHost(
                        navController = navController,
                        startDestination = "dashboard"
                    )
                    {
                        composable("splash") {
                            SplashScreen(navController)
                        }
                        composable("userLogin") {
                            UserLoginScreen(navController,api)
                        }
                        composable("dashboard") {
                            DashboardScreen(navController)
                        }
                        composable("accountscreen") {
                            AccountScreen(navController)
                        }
                        composable("orderscreen") {
                            OrderScreen(navController)
                        }
                        composable("history") {
                            OrderHistoryScreen(
                                listOf(
                                    Order(
                                        name = "Paneer Butter Masala",
                                        date = "25 Aug 2025",
                                        orderId = "#12345",
                                        price = "₹250",
                                        status = "Delivered"
                                    ),
                                    Order(
                                        name = "Veg Burger",
                                        date = "22 Aug 2025",
                                        orderId = "#12346",
                                        price = "₹120",
                                        status = "Delivered"
                                    )
                                )
                            )
                        }
                        composable ("registration"){
                            RegistrationScreen(navController,api)
                        }
                        composable("settings"){
                            SettingsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
