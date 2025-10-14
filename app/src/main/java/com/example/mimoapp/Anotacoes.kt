package com.example.mimoapp

import android.R.attr.onClick
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
    var notas by remember { mutableStateOf<List<NotasEntity>>(emptyList()) }
    var notaSelecionada by remember { mutableStateOf<NotasEntity?>(null) }

    val textoBotao = if (notaSelecionada == null) "Adicionar nota" else "Atualizar nota"

    val scope = rememberCoroutineScope()
    val contex = LocalContext.current
    val db = AppDatabase.getDatabase(contex)
    val notasDao = db.notasDAO()

    LaunchedEffect(Unit) {
        notas = buscarNotas(notasDao)
    }

    LaunchedEffect(notaSelecionada) {
        if (notaSelecionada != null) {
            titulo = notaSelecionada!!.titulo
            descricao = notaSelecionada!!.descricao
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
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
            modifier = Modifier.fillMaxWidth(),
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
            onValueChange = { descricao = it },
            label = {
                Text(
                    "Descrição",
                    color = Color.White
                )
            },

            placeholder = { Text("Digite os detalhes da sua nota aqui") },

            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),

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
        ) {
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        if (titulo.isNotBlank() && descricao.isNotBlank()) {

                            if (notaSelecionada == null) {
                                save(titulo, descricao, notasDao)
                            } else {

                                val notaAtualizada = notaSelecionada!!.copy(titulo = titulo, descricao = descricao)
                                update(notaAtualizada, notasDao)
                            }

                            notas = buscarNotas(notasDao)
                            titulo = ""
                            descricao = ""
                            notaSelecionada = null

                        }
                    }
                },
                contentPadding = PaddingValues(10.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(24, 104, 238),
                    contentColor = Color.White
                )
            ) {
                Text(textoBotao)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(notas) { nota ->
                NotaCard(
                    nota = nota,
                    onClick = {
                        notaSelecionada = nota
                    },

                    onDeleteClick = {
                        scope.launch {
                            deletar(nota, notasDao)
                            notas = buscarNotas(notasDao)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun NotaCard(
    nota: NotasEntity,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                    Text(
                        text = nota.titulo,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = nota.descricao,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray
                    )

            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onDeleteClick,

                modifier = Modifier.padding(15.dp),
                shape = CircleShape,


                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(15,82,186),
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Deletar"
                )

            }

        }
    }
}



