package com.example.mimoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mimoapp.data.local.AppDatabase
import com.example.mimoapp.data.local.NotasEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mimoapp.data.repository.NotasRepository
import com.example.mimoapp.ui.notas.NotasViewModel

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

    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context.applicationContext)
    val repository = NotasRepository(db.notasDAO())

    val viewModel: NotasViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(NotasViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return NotasViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    )

    val titulo by viewModel.titulo.collectAsStateWithLifecycle()
    val descricao by viewModel.descricao.collectAsStateWithLifecycle()
    val notas by viewModel.notas.collectAsStateWithLifecycle()
    val textoBotao by viewModel.textoBotao.collectAsStateWithLifecycle()


    Scaffold { innerPadding ->

                Surface(
                    color = Color(24, 104, 238, 255),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            color = Color(255, 255, 255, 255)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(15.dp)
                            ) {
                                CabecalhoNotas()

                                Spacer(modifier = Modifier.height(12.dp))

                                FormularioNotas(
                                    titulo = titulo,
                                    onTituloChange = { viewModel.onTituloChange(it) },
                                    descricao = descricao,
                                    onDescricaoChange = { viewModel.onDescricaoChange(it) },
                                    textoBotao = textoBotao,
                                    onSalvarClick = { viewModel.salvarOuAtualizarNota() },
                                    notas = notas,
                                    onNotaClick = { viewModel.selecionarNota(it) },
                                    onDeleteClick = { viewModel.deletarNota(it) }
                                )

                                Spacer(modifier = Modifier.weight(1f))
                                Rodape(navController = navController)

                            }
                        }

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
fun FormularioNotas(
    titulo: String,
    onTituloChange: (String) -> Unit,
    descricao: String,
    onDescricaoChange: (String) -> Unit,
    textoBotao: String,
    onSalvarClick: () -> Unit,
    notas: List<NotasEntity>,
    onNotaClick: (NotasEntity) -> Unit,
    onDeleteClick: (NotasEntity) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        TextField(
            value = titulo,
            onValueChange = onTituloChange,
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
            onValueChange = onDescricaoChange,
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
                onClick = onSalvarClick,
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
                    onClick = { onNotaClick(nota) },
                    onDeleteClick = { onDeleteClick(nota) }
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



