package com.example.mimoapp.data.repository

import com.example.mimoapp.data.local.NotasEntity
import com.example.mimoapp.data.local.NotasDAO
import kotlinx.coroutines.flow.Flow

class NotasRepository (private val notasDao: NotasDAO) {

    fun buscarNotas(): Flow<List<NotasEntity>> = notasDao.buscarNotas()

    suspend fun save(titulo: String, descricao: String) {
        val nota = NotasEntity(titulo = titulo, descricao = descricao)
        notasDao.save(nota)
    }

    suspend fun update(notas : NotasEntity){
        notasDao.update(notas)
    }

    suspend fun deletar(notas: NotasEntity){
        notasDao.deletar(notas)
    }

}
