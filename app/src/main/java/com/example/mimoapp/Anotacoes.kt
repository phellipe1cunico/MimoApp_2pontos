package com.example.mimoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mimoapp.ui.theme.MimoAppTheme

class Anotacoes : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

@Composable
fun TelaAnotacoes(navController: NavController) {

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
                        Surface(
                            color = Color(15, 82, 186)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(15.dp)
                            ) {
                                CabecalhoNotas()

                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        FormularioNotas()

                        Spacer(modifier = Modifier.weight(1f))
                        Rodape(navController = navController)
                    }
                }
            }
        }


@Composable
fun CabecalhoNotas(){
    Surface(
        color = Color(24, 104, 238, 255),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {

                Text(
                    text = "Anotações",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Registre aqui suas anotações sobre o conteúdo 😎",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )

            }

        }
    }
}

@Composable
fun FormularioNotas() {

    var titulo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf<List<NotasEntity>>(emptyList())}

    val contex = LocalContext.current
    val db = AppDatabase.getDatabase(contex)
    val notasDao = db.notasDAO()

    LaunchedEffect(Unit) {
        notas = buscarNotas(notasDao)
    }

    Column(
    ) {
        TextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = {
                Text(
                    "Título",
                    color = Color.White
                )
            },
            placeholder = { Text("Título da Nota") },
            modifier = Modifier.fillMaxWidth().padding(15.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,

                focusedLabelColor = Color.White,
                focusedPlaceholderColor = Color.White,

                unfocusedContainerColor = Color(37, 111, 234, 255),
                focusedContainerColor = Color(43, 114, 231, 255)
            )
        )

        Spacer(modifier = Modifier.height(5.dp))

        TextField(
            value = descricao,
            onValueChange = { descricao = it},
            label = {
                Text(
                    "Descrição",
                    color = Color.White
                )
            },

            placeholder = { Text("Digite os detalhes da sua nota aqui") },

            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(15.dp),

            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,

                focusedLabelColor = Color.White,
                focusedPlaceholderColor = Color.White,

                unfocusedContainerColor = Color(37, 111, 234, 255),
                focusedContainerColor = Color(43, 114, 231, 255)
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                onClick = {


                },
                contentPadding = PaddingValues(10.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(24, 104, 238),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Adicionar nota"
                )
            }
        }
    }

}

@Composable
fun BotaoNota(){


}


