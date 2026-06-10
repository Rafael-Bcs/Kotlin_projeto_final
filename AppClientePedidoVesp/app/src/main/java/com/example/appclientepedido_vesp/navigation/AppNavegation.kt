package com.example.appclientepedido_vesp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appclientepedido_vesp.view.ClientesScreen
import com.example.appclientepedido_vesp.view.Home
import com.example.appclientepedido_vesp.view.PedidosScreen

@Composable
fun AppNavigation(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home"){
            Home(navController)
        }

        composable("clientes"){
            ClientesScreen(navController)
        }

        composable("pedidos"){
            PedidosScreen(navController)
        }


    }

}