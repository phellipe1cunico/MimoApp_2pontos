package com.example.mimoapp

import android.util.Log

suspend fun buscarNotas(notasDao: NotasDAO): List<NotasEntity> {
    return try {
        notasDao.buscarNotas()
    } catch (e: Exception) {
        Log.e("Erro ao buscar notas", "${e.message}")
        emptyList()
    }
}

suspend fun add(titulo: String, descricao: String, notasDao: NotasDAO) {
    try {
        notasDao.save(NotasEntity(titulo = titulo, descricao = descricao))
    } catch (e: Exception) {
        Log.e("Erro ao adicionar", "Msg: ${e.message}")
    }
}

suspend fun deletar(notas: NotasEntity, notasDao: NotasDAO) {
    try {
        notasDao.delete(notas)
    } catch (e: Exception) {
        Log.e("Erro ao deletar", "Msg: ${e.message}")
    }
}

suspend fun update(notas: NotasEntity, notasDao: NotasDAO) {
    try {
        notasDao.update(notas)
    } catch (e: Exception) {
        Log.e("Erro ao dar update", "Msg: ${e.message}")
    }
}
