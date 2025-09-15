package com.example.mimoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mimoapp.ui.theme.MimoAppTheme

class Leaderboard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MimoAppTheme {
                TelaLeaderboard()

            }
        }
    }
}

@Preview
@Composable
fun TelaLeaderboard() {
    Scaffold { innerPadding ->

        Surface(
            color = Color(255, 250, 250),
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    color = Color(15, 82, 186)
                )
                {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(15.dp)
                    ) {
                        Cabecalho1()
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Rodape()
            }
        }
    }
}