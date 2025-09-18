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

    val opcoes by remember { mutableStateOf(listOf("func", "fun", "function", "def")) }
    val respostaCorreta = "fun"

    // Novo estado para controlar qual resposta foi selecionada. null = nenhuma.
    var respostaSelecionada by remember { mutableStateOf<String?>(null) }
    val isAnswered = respostaSelecionada != null

    Scaffold { innerPadding ->
        Surface(
            color = Color(255, 250, 250),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // --- Topo da tela (Barra azul com Vidas) - Sem alterações ---
                Surface(
                    color = Color(24, 104, 238, 255),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Botão Fechar
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Fechar",
                            tint = Color.White,
                            modifier = Modifier.clickable { navController.popBackStack() }
                        )
                        // Vidas
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Favorite, contentDescription = "Vidas", tint = Color.Red)
                            Spacer(modifier = Modifier.size(6.dp))
                            Text("5", color = Color.White, style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }

                // --- Enunciado - Sem alterações ---
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

                // --- Lista de Opções com a nova lógica ---
                LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                    items(opcoes) { op ->
                        Opcoes(
                            texto = op,
                            isAnswered = isAnswered,
                            isSelected = op == respostaSelecionada,
                            isCorrect = op == respostaCorreta,
                            onClick = {
                                if (!isAnswered) { // Permite o clique apenas uma vez
                                    respostaSelecionada = op
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

                Spacer(modifier = Modifier.weight(1f))


                if (isAnswered) {
                    val isCorrect = respostaSelecionada == respostaCorreta

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        Button(
                            onClick = {
                                navController.popBackStack()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isCorrect) Color(46, 139, 87) else Color(178, 34, 34)
                            )
                        ) {
                            Text(text = "Continuar", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }
    }
}

    @Composable
    fun Opcoes(
            texto: String,
            isAnswered: Boolean,
            isSelected: Boolean,
            isCorrect: Boolean,
            onClick: () -> Unit
        ) {

            val containerColor = when {
                !isAnswered -> Color(24, 104, 238, 255)
                isSelected && isCorrect -> Color(46, 139, 87)
                isSelected && !isCorrect -> Color(
                    178,
                    34,
                    34
                )
                !isSelected && isCorrect -> Color(
                    46,
                    139,
                    87
                )
                else -> Color.LightGray
            }

            Card(
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                onClick = onClick,
                enabled = !isAnswered,
                colors = CardDefaults.cardColors(
                    containerColor = containerColor,
                    contentColor = Color.White,
                    disabledContainerColor = containerColor.copy(alpha = 0.7f),
                    disabledContentColor = Color.White
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = texto,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }







