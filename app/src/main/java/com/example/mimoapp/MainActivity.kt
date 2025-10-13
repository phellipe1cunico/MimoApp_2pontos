package com.example.mimoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mimoapp.ui.theme.MimoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MimoAppTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "principal") {
                    composable("principal") {TelaInicial(navController)}
                    composable("anotacoes") {TelaAnotacoes(navController)}
                    composable("leaderboard"){TelaLeaderboard(navController)}
                    composable("perfil") {TelaPerfil(navController)}
                    composable("exercicio") {TelaExercicio(navController)}
                }
            }
        }
    }
}
