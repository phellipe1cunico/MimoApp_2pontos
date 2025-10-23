package com.example.mimoapp.data.repository

import android.util.Log
import com.example.mimoapp.data.local.NotasEntity
import com.example.mimoapp.data.local.PerfilDAO
import com.example.mimoapp.data.local.PerfilEntity
import kotlinx.coroutines.flow.Flow

class PerfilRepository(private val perfilDao: PerfilDAO) {


    suspend fun salvarPerfil(nome: String, email: String): PerfilEntity? {
        return try {
            val perfil = PerfilEntity(nome = nome, email = email)
            perfilDao.save(perfil)
            perfil
        } catch (e: Exception) {
            Log.e("Erro ao salvar perfil", "Msg: ${e.message}")
            null
        }
    }

    suspend fun buscarPerfilAtual(): PerfilEntity? {
        return try {
            perfilDao.buscarPerfilAtual()
        } catch (e: Exception) {
            Log.e("Erro ao buscar perfil", "Msg: ${e.message}")
            null
        }
    }

    fun buscarTodosPerfis(): Flow<List<PerfilEntity>> {
        return perfilDao.buscarTodosPerfis()
    }

    suspend fun atualizarPerfil(perfil: PerfilEntity): Boolean {
        return try {
            perfilDao.update(perfil)
            true
        } catch (e: Exception) {
            Log.e("Erro ao atualizar perfil", "Msg: ${e.message}")
            false
        }
    }

    suspend fun deletarPerfil(perfil: PerfilEntity): Boolean {
        return try {
            perfilDao.deletar(perfil)
            true
        } catch (e: Exception) {
            Log.e("Erro ao deletar perfil", "Msg: ${e.message}")
            false
        }
    }

    suspend fun criarPerfilPadrao(): PerfilEntity? {
        return try {
            val perfilExistente = perfilDao.buscarPerfilAtual()
            if (perfilExistente == null) {
                val perfilPadrao = PerfilEntity(
                    nome = "Usuário",
                    email = "usuario@exemplo.com",
                    nivel = "Iniciante",
                    pontos = 0,
                    certificados = 0,
                    amigos = 0
                )
                perfilDao.save(perfilPadrao)
                perfilPadrao
            } else {
                perfilExistente
            }
        } catch (e: Exception) {
            Log.e("Erro ao criar perfil padrão", "Msg: ${e.message}")
            null
        }
    }

}