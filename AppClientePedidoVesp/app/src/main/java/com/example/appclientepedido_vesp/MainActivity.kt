package com.example.appclientepedido_vesp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.appclientepedido_vesp.navigation.AppNavigation
import com.example.appclientepedido_vesp.ui.theme.AppClientePedido_VespTheme
import com.example.appclientepedido_vesp.view.Home

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppClientePedido_VespTheme {
                AppNavigation()
            }
        }
    }
}
