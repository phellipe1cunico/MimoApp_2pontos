package com.example.mimoapp

import android.util.Log

// CREATE - Salvar novo perfil
suspend fun salvarPerfil(nome: String, email: String, perfilDao: PerfilDAO): PerfilEntity? {
    return try {
        val perfil = PerfilEntity(nome = nome, email = email)
        perfilDao.save(perfil)
        perfil
    } catch (e: Exception) {
        Log.e("Erro ao salvar perfil", "Msg: ${e.message}")
        null
    }
}

// READ - Buscar perfil atual
suspend fun buscarPerfilAtual(perfilDao: PerfilDAO): PerfilEntity? {
    return try {
        perfilDao.buscarPerfilAtual()
    } catch (e: Exception) {
        Log.e("Erro ao buscar perfil", "Msg: ${e.message}")
        null
    }
}

// READ - Buscar perfil por ID
suspend fun buscarPerfilPorId(id: Int, perfilDao: PerfilDAO): PerfilEntity? {
    return try {
        perfilDao.buscarPerfilPorId(id)
    } catch (e: Exception) {
        Log.e("Erro ao buscar perfil por ID", "Msg: ${e.message}")
        null
    }
}

// READ - Buscar todos os perfis
suspend fun buscarTodosPerfis(perfilDao: PerfilDAO): List<PerfilEntity> {
    return try {
        perfilDao.buscarTodosPerfis()
    } catch (e: Exception) {
        Log.e("Erro ao buscar todos os perfis", "Msg: ${e.message}")
        emptyList()
    }
}

// UPDATE - Atualizar perfil completo
suspend fun atualizarPerfil(perfil: PerfilEntity, perfilDao: PerfilDAO): Boolean {
    return try {
        perfilDao.update(perfil)
        true
    } catch (e: Exception) {
        Log.e("Erro ao atualizar perfil", "Msg: ${e.message}")
        false
    }
}

// UPDATE - Atualizar apenas pontos
suspend fun atualizarPontosPerfil(id: Int, pontos: Int, perfilDao: PerfilDAO): Boolean {
    return try {
        perfilDao.atualizarPontos(id, pontos)
        true
    } catch (e: Exception) {
        Log.e("Erro ao atualizar pontos", "Msg: ${e.message}")
        false
    }
}

// UPDATE - Atualizar apenas nível
suspend fun atualizarNivelPerfil(id: Int, nivel: String, perfilDao: PerfilDAO): Boolean {
    return try {
        perfilDao.atualizarNivel(id, nivel)
        true
    } catch (e: Exception) {
        Log.e("Erro ao atualizar nível", "Msg: ${e.message}")
        false
    }
}

// UPDATE - Atualizar certificados
suspend fun atualizarCertificadosPerfil(id: Int, certificados: Int, perfilDao: PerfilDAO): Boolean {
    return try {
        perfilDao.atualizarCertificados(id, certificados)
        true
    } catch (e: Exception) {
        Log.e("Erro ao atualizar certificados", "Msg: ${e.message}")
        false
    }
}

// UPDATE - Atualizar amigos
suspend fun atualizarAmigosPerfil(id: Int, amigos: Int, perfilDao: PerfilDAO): Boolean {
    return try {
        perfilDao.atualizarAmigos(id, amigos)
        true
    } catch (e: Exception) {
        Log.e("Erro ao atualizar amigos", "Msg: ${e.message}")
        false
    }
}

// DELETE - Deletar perfil
suspend fun deletarPerfil(perfil: PerfilEntity, perfilDao: PerfilDAO): Boolean {
    return try {
        perfilDao.deletar(perfil)
        true
    } catch (e: Exception) {
        Log.e("Erro ao deletar perfil", "Msg: ${e.message}")
        false
    }
}

// DELETE - Deletar perfil por ID
suspend fun deletarPerfilPorId(id: Int, perfilDao: PerfilDAO): Boolean {
    return try {
        perfilDao.deletarPorId(id)
        true
    } catch (e: Exception) {
        Log.e("Erro ao deletar perfil por ID", "Msg: ${e.message}")
        false
    }
}

// UTILITY - Criar perfil padrão se não existir
suspend fun criarPerfilPadrao(perfilDao: PerfilDAO): PerfilEntity? {
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

