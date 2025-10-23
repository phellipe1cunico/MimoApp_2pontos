package com.example.mimoapp.ui.notas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mimoapp.data.local.NotasEntity
import com.example.mimoapp.data.repository.NotasRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.map

class NotasViewModel(private val repository: NotasRepository) : ViewModel() {

    val notas: StateFlow<List<NotasEntity>> = repository.buscarNotas()
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    private val _titulo = MutableStateFlow("")
    val titulo: StateFlow<String> = _titulo.asStateFlow()

    private val _descricao = MutableStateFlow("")
    val descricao: StateFlow<String> = _descricao.asStateFlow()

    private val _notaSelecionada = MutableStateFlow<NotasEntity?>(null)
    val notaSelecionada: StateFlow<NotasEntity?> = _notaSelecionada.asStateFlow()

    val textoBotao: StateFlow<String> = _notaSelecionada.map { notaSelecionada ->
        if (notaSelecionada == null) {
            "Adicionar nota"
        } else {
            "Atualizar nota"
        }
    }.stateIn(
        scope = viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000L),
        initialValue = "Adicionar nota"
    )

    fun onTituloChange(novoTitulo: String) {
        _titulo.value = novoTitulo
    }

    fun onDescricaoChange(novaDescricao: String) {
        _descricao.value = novaDescricao
    }

    fun selecionarNota(nota: NotasEntity) {
        _notaSelecionada.value = nota
        _titulo.value = nota.titulo
        _descricao.value = nota.descricao
    }

    fun limparSelecao() {
        _notaSelecionada.value = null
        _titulo.value = ""
        _descricao.value = ""
    }

    fun salvarOuAtualizarNota() {
        if (_titulo.value.isBlank() || _descricao.value.isBlank()) return

        viewModelScope.launch(Dispatchers.IO) {
            val notaSelecionadaAtual = _notaSelecionada.value
            if (notaSelecionadaAtual == null) {
                repository.save(_titulo.value, _descricao.value)
            } else {
                val notaAtualizada = notaSelecionadaAtual.copy(
                    titulo = _titulo.value,
                    descricao = _descricao.value
                )
                repository.update(notaAtualizada)
            }
            // Limpa os campos após a operação
            limparSelecao()
        }
    }

    fun deletarNota(nota: NotasEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletar(nota)
        }
    }
}


