package com.example.mimoapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotasDAO{

    @Insert
    fun add(notas: Notas)

    @Query("SELECT * FROM notas")
    fun buscarNotas() : List<Notas>
}