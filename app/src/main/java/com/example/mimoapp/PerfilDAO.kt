package com.example.mimoapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PerfilDAO {
    
    @Insert
    suspend fun save(perfil: PerfilEntity)
    
    @Query("SELECT * FROM perfil WHERE id = :id")
    suspend fun buscarPerfilPorId(id: Int): PerfilEntity?
    
    @Query("SELECT * FROM perfil LIMIT 1")
    suspend fun buscarPerfilAtual(): PerfilEntity?
    
    @Query("SELECT * FROM perfil")
    fun buscarTodosPerfis(): Flow<List<PerfilEntity>>
    
    @Update
    suspend fun update(perfil: PerfilEntity)
    
    @Delete
    suspend fun deletar(perfil: PerfilEntity)
    
    @Query("DELETE FROM perfil WHERE id = :id")
    suspend fun deletarPorId(id: Int)
    
    @Query("UPDATE perfil SET pontos = :pontos WHERE id = :id")
    suspend fun atualizarPontos(id: Int, pontos: Int)
    
    @Query("UPDATE perfil SET nivel = :nivel WHERE id = :id")
    suspend fun atualizarNivel(id: Int, nivel: String)
    
    @Query("UPDATE perfil SET certificados = :certificados WHERE id = :id")
    suspend fun atualizarCertificados(id: Int, certificados: Int)
    
    @Query("UPDATE perfil SET amigos = :amigos WHERE id = :id")
    suspend fun atualizarAmigos(id: Int, amigos: Int)
}

