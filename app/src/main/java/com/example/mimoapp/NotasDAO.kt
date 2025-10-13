package com.example.mimoapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotasDAO{

    @Insert
    suspend fun save(notas: NotasEntity)
    //nome da tabela: data class

    @Query("SELECT * FROM notas")
    suspend fun buscarNotas() : List<NotasEntity>
                                //data class
    @Delete
    suspend fun delete(notas: NotasEntity)

    @Update
    suspend fun update(notas: NotasEntity)
}