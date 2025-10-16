package com.example.mimoapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotasDAO{

    @Insert
    suspend fun save(notas: NotasEntity)
    //nome da tabela: data class

    @Query("SELECT * FROM notas")
    suspend fun buscarNotas() : Flow<List<NotasEntity>>
                                //data class
    @Delete
    suspend fun deletar(notas: NotasEntity)

    @Update
    suspend fun update(notas: NotasEntity)
}