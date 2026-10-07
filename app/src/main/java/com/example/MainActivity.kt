package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ui.HomeScreen
import com.example.ui.theme.TestTheme
import com.example.ui.LoginScreen
import com.example.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {

    private val viewModel : LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTheme {

                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = "login"
                        ) {
                            // Pantalla Login
                            composable(route = "login") {
                                LoginScreen(
                                    viewModel = viewModel(),
                                    onLoginSuccess = {
                                        //
                                        val rutaDestino = when (viewModel.rolActivo) {
                                            "Admin" -> "home_admin"
                                            "Supervisor" -> "home_supervisor"
                                            else -> "home_operador"
                                        }
                                        navController.navigate(route = rutaDestino) {
                                            popUpTo(route = "login") { inclusive = true }
                                        }
                                    }
                                )
                            }

                            // Pantalla Principal - Rol Administrador
                            composable(route = "home_admin") {
                                HomeScreen(
                                    viewModel = viewModel(),
                                    onCerrarSesion = {
                                        navController.navigate(route = "login") {
                                            popUpTo(route = "home_admin") { inclusive = true }
                                        }
                                    }
                                )
                            }

                            // Pantalla Principal - Rol Supervisor
                            composable(route = "home_supervisor") {
                                HomeScreen(
                                    viewModel = viewModel(),
                                    onCerrarSesion = {
                                        navController.navigate(route = "login") {
                                            popUpTo(route = "home_supervisor") { inclusive = true }
                                        }
                                    }
                                )
                            }

                            // Pantalla Principal - Rol Operador
                            composable(route = "home_operador") {
                                HomeScreen(
                                    viewModel = viewModel(),
                                    onCerrarSesion = {
                                        navController.navigate(route = "login") {
                                            popUpTo(route = "home_operador") { inclusive = true }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

