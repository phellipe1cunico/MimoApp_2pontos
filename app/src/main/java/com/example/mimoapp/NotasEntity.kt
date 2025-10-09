package com.example.mimoapp

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notas")
data class Notas(


    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,


    val titulo: String,
    val descricao: String,
    )

