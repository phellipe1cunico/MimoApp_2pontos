package com.example.mimoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mimoapp.ui.theme.MimoAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.MutableState


class Exercicio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

@Composable
fun TelaExercicio(navController: NavController) {

    val opcoes by remember {mutableStateOf(listOf("func", "fun", "function", "def")) }

    var resp by remember { mutableStateOf("")}

    Scaffold { innerPadding ->
        Surface(
            color = Color(255, 250, 250),
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Surface(
                    color = Color(24, 104, 238, 255),
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Button(
                                onClick = {
                                    navController.popBackStack()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(24, 104, 238, 255),
                                    contentColor = Color.White
                                )
                            ) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "Fechar",
                                    tint = Color.White
                                )
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Vidas",
                                tint = Color.Red
                            )

                            Spacer(modifier = Modifier.size(6.dp))

                            Text(
                                text = "5",
                                color = Color.White,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }

                // Enunciado
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Qual o termo correto para uma função em Kotlin?",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(40, 40, 40)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn {
                    items(opcoes){
                        op ->
                        Opcoes(op)
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }


                Spacer(modifier = Modifier.weight(1f))

                // Resposta + Próxima
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        TextField(
                            value = resp ,
                            onValueChange = { novaResp ->
                                resp = novaResp
                            }
                        )

                        Button(
                            onClick = { },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(24, 104, 238, 255),
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Próxima")
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun Opcoes(texto: String = "") {

    Card(
        modifier = Modifier
            .width(200.dp)
            .height(50.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color(24, 104, 238, 255),
            contentColor = Color.White
        )
    ) {

        Row (
            modifier = Modifier.fillMaxHeight().padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){

            Icon(
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = "Icon arrow"
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = texto,
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }



}





