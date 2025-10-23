package com.example.mimoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mimoapp.data.local.AppDatabase
import com.example.mimoapp.data.local.PerfilEntity
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mimoapp.data.repository.PerfilRepository
import com.example.mimoapp.ui.perfil.PerfilViewModel

class PerfilActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

@Composable
fun TelaPerfil(navController: NavController) {
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context.applicationContext)
    val repository = PerfilRepository(db.perfilDAO())

    val viewModel: PerfilViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(PerfilViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return PerfilViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    )

    val perfil by viewModel.perfil.collectAsStateWithLifecycle()
    val isEditing by viewModel.isEditing.collectAsStateWithLifecycle()
    val showDeleteDialog by viewModel.showDeleteDialog.collectAsStateWithLifecycle()
    val showCreateDialog by viewModel.showCreateDialog.collectAsStateWithLifecycle()

    // Coletar estado dos formulários
    val nome by viewModel.nome.collectAsStateWithLifecycle()
    val email by viewModel.email.collectAsStateWithLifecycle()
    val nivel by viewModel.nivel.collectAsStateWithLifecycle()
    val pontos by viewModel.pontos.collectAsStateWithLifecycle()
    val certificados by viewModel.certificados.collectAsStateWithLifecycle()
    val amigos by viewModel.amigos.collectAsStateWithLifecycle()

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
                // Top bar "Perfil"
                Surface(
                    color = Color(24, 104, 238, 255),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Perfil",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )

                        Row {
                            if (perfil != null) {
                                Button(
                                    onClick = { viewModel.onEditToggle() },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent,
                                        contentColor = Color.White
                                    )
                                ) {
                                    Icon(
                                        imageVector = if (isEditing) Icons.Default.Check else Icons.Default.Edit,
                                        contentDescription = if (isEditing) "Salvar" else "Editar"
                                    )
                                }
                            } else {
                                Button(
                                    onClick = { viewModel.onShowCreateDialog(true) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent,
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text("Criar Perfil")
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Avatar circle
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape)
                        .background(Color(220, 230, 255))
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Avatar",
                        modifier = Modifier.size(40.dp),
                        tint = Color(24, 104, 238)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))


                if (isEditing) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextField(
                            value = nome,
                            onValueChange = { viewModel.onNomeChange(it) },
                            label = { Text("Nome") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = email,
                            onValueChange = { viewModel.onEmailChange(it) },
                            label = { Text("Email") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = nivel,
                            onValueChange = { viewModel.onNivelChange(it) },
                            label = { Text("Nível") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = pontos,
                            onValueChange = { viewModel.onPontosChange(it) },
                            label = { Text("Pontos") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = certificados,
                            onValueChange = { viewModel.onCertificadosChange(it) },
                            label = { Text("Certificados") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = amigos,
                            onValueChange = { viewModel.onAmigosChange(it) },
                            label = { Text("Amigos") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = { viewModel.salvarEdicoes() },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(46, 139, 87)
                                )
                            ) {
                                Text("Salvar")
                            }

                            Button(
                                onClick = { viewModel.onShowDeleteDialog(true) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(178, 34, 34)
                                )
                            ) {
                                Text("Deletar")
                            }
                        }
                    }
                } else {
                    // View Mode
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = perfil?.nome ?: "Sem perfil",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color(60, 60, 60),
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = perfil?.email ?: "",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color(100, 100, 100)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Nível: ${perfil?.nivel ?: "N/A"}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(24, 104, 238),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Stats Cards
                if (perfil != null) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatCard(
                            title = "Pontos",
                            value = perfil!!.pontos.toString(),
                            icon = Icons.Default.Star,
                            color = Color(255, 193, 7)
                        )

                        StatCard(
                            title = "Certificados",
                            value = perfil!!.certificados.toString(),
                            icon = Icons.Default.Home,
                            color = Color(76, 175, 80)
                        )

                        StatCard(
                            title = "Amigos",
                            value = perfil!!.amigos.toString(),
                            icon = Icons.Default.Person,
                            color = Color(33, 150, 243)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
                Rodape(navController = navController)
            }
        }
    }

    // Create Profile Dialog
    if (showCreateDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.onShowCreateDialog(false) },
            title = { Text("Criar Perfil") },
            text = {
                Column {
                    TextField(
                        value = nome,
                        onValueChange = { viewModel.onNomeChange(it) },
                        label = { Text("Nome") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = email,
                        onValueChange = { viewModel.onEmailChange(it) },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(onClick = { viewModel.criarNovoPerfil() }) {
                    Text("Criar")
                }
            },
            dismissButton = {
                Button(onClick = { viewModel.onShowCreateDialog(false) }) {
                    Text("Cancelar")
                }
            }
        )
    }

    // Delete Confirmation Dialog
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.onShowDeleteDialog(false) },
            title = { Text("Deletar Perfil") },
            text = { Text("Tem certeza que deseja deletar seu perfil? Esta ação não pode ser desfeita.") },
            confirmButton = {
                Button(
                    onClick = { viewModel.deletarPerfil() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(178, 34, 34)
                    )
                ) {
                    Text("Deletar")
                }
            },
            dismissButton = {
                Button(onClick = { viewModel.onShowDeleteDialog(false) }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(80.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(60, 60, 60)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = Color(100, 100, 100)
            )
        }
    }
}
