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
import kotlinx.coroutines.launch

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
    var perfil by remember { mutableStateOf<PerfilEntity?>(null) }
    var isEditing by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showCreateDialog by remember { mutableStateOf(false) }
    
    // Form states
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var nivel by remember { mutableStateOf("Iniciante") }
    var pontos by remember { mutableStateOf("0") }
    var certificados by remember { mutableStateOf("0") }
    var amigos by remember { mutableStateOf("0") }
    
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val perfilDao = db.perfilDAO()

    // Load profile on startup
    LaunchedEffect(Unit) {
        perfil = buscarPerfilAtual(perfilDao) ?: criarPerfilPadrao(perfilDao)
        perfil?.let {
            nome = it.nome
            email = it.email
            nivel = it.nivel
            pontos = it.pontos.toString()
            certificados = it.certificados.toString()
            amigos = it.amigos.toString()
        }
    }

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
                                    onClick = { isEditing = !isEditing },
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
                                    onClick = { showCreateDialog = true },
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

                // Profile Information
                if (isEditing) {
                    // Edit Mode
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextField(
                            value = nome,
                            onValueChange = { nome = it },
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
                            onValueChange = { email = it },
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
                            onValueChange = { nivel = it },
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
                            onValueChange = { pontos = it },
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
                            onValueChange = { certificados = it },
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
                            onValueChange = { amigos = it },
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
                                onClick = {
                                    scope.launch {
                                        perfil?.let { currentPerfil ->
                                            val updatedPerfil = currentPerfil.copy(
                                                nome = nome,
                                                email = email,
                                                nivel = nivel,
                                                pontos = pontos.toIntOrNull() ?: 0,
                                                certificados = certificados.toIntOrNull() ?: 0,
                                                amigos = amigos.toIntOrNull() ?: 0
                                            )
                                            if (atualizarPerfil(updatedPerfil, perfilDao)) {
                                                perfil = updatedPerfil
                                                isEditing = false
                                            }
                                        }
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(46, 139, 87)
                                )
                            ) {
                                Text("Salvar")
                            }
                            
                            Button(
                                onClick = { showDeleteDialog = true },
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
            onDismissRequest = { showCreateDialog = false },
            title = { Text("Criar Perfil") },
            text = {
                Column {
                    TextField(
                        value = nome,
                        onValueChange = { nome = it },
                        label = { Text("Nome") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        scope.launch {
                            val novoPerfil = salvarPerfil(nome, email, perfilDao)
                            if (novoPerfil != null) {
                                perfil = novoPerfil
                                showCreateDialog = false
                            }
                        }
                    }
                ) {
                    Text("Criar")
                }
            },
            dismissButton = {
                Button(onClick = { showCreateDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    // Delete Confirmation Dialog
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Deletar Perfil") },
            text = { Text("Tem certeza que deseja deletar seu perfil? Esta ação não pode ser desfeita.") },
            confirmButton = {
                Button(
                    onClick = {
                        scope.launch {
                            perfil?.let {
                                if (deletarPerfil(it, perfilDao)) {
                                    perfil = null
                                    isEditing = false
                                    showDeleteDialog = false
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(178, 34, 34)
                    )
                ) {
                    Text("Deletar")
                }
            },
            dismissButton = {
                Button(onClick = { showDeleteDialog = false }) {
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
