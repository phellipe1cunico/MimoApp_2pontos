package com.example.mimoapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "perfil")
data class PerfilEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val email: String,
    val nivel: String = "Iniciante",
    val pontos: Int = 0,
    val certificados: Int = 0,
    val amigos: Int = 0,
    val dataCriacao: Long = System.currentTimeMillis()
)

