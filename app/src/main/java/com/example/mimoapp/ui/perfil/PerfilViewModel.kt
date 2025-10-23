package com.example.mimoapp.ui.perfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mimoapp.data.local.PerfilEntity
import com.example.mimoapp.data.repository.PerfilRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PerfilViewModel(private val repository: PerfilRepository) : ViewModel() {

    private val _perfil = MutableStateFlow<PerfilEntity?>(null)
    val perfil: StateFlow<PerfilEntity?> = _perfil.asStateFlow()

    private val _isEditing = MutableStateFlow(false)
    val isEditing: StateFlow<Boolean> = _isEditing.asStateFlow()

    private val _showDeleteDialog = MutableStateFlow(false)
    val showDeleteDialog: StateFlow<Boolean> = _showDeleteDialog.asStateFlow()

    private val _showCreateDialog = MutableStateFlow(false)
    val showCreateDialog: StateFlow<Boolean> = _showCreateDialog.asStateFlow()


    private val _nome = MutableStateFlow("")
    val nome: StateFlow<String> = _nome.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _nivel = MutableStateFlow("")
    val nivel: StateFlow<String> = _nivel.asStateFlow()

    private val _pontos = MutableStateFlow("0")
    val pontos: StateFlow<String> = _pontos.asStateFlow()

    private val _certificados = MutableStateFlow("0")
    val certificados: StateFlow<String> = _certificados.asStateFlow()

    private val _amigos = MutableStateFlow("0")
    val amigos: StateFlow<String> = _amigos.asStateFlow()

    init {
        carregarPerfilInicial()
    }

    private fun carregarPerfilInicial() {
        viewModelScope.launch(Dispatchers.IO) {
            val perfilAtual = repository.buscarPerfilAtual() ?: repository.criarPerfilPadrao()
            _perfil.value = perfilAtual
            
            perfilAtual?.let {
                _nome.value = it.nome
                _email.value = it.email
                _nivel.value = it.nivel
                _pontos.value = it.pontos.toString()
                _certificados.value = it.certificados.toString()
                _amigos.value = it.amigos.toString()
            }
        }
    }

    fun onNomeChange(novoNome: String) { _nome.value = novoNome }
    fun onEmailChange(novoEmail: String) { _email.value = novoEmail }
    fun onNivelChange(novoNivel: String) { _nivel.value = novoNivel }
    fun onPontosChange(novosPontos: String) { _pontos.value = novosPontos }
    fun onCertificadosChange(novosCertificados: String) { _certificados.value = novosCertificados }
    fun onAmigosChange(novosAmigos: String) { _amigos.value = novosAmigos }

    fun onEditToggle() {
        _isEditing.value = !_isEditing.value

        if (!_isEditing.value) {
            _perfil.value?.let {
                _nome.value = it.nome
                _email.value = it.email

            }
        }
    }

    fun salvarEdicoes() {
        val perfilAtual = _perfil.value ?: return

        val perfilAtualizado = perfilAtual.copy(
            nome = _nome.value,
            email = _email.value,
            nivel = _nivel.value,
            pontos = _pontos.value.toIntOrNull() ?: 0,
            certificados = _certificados.value.toIntOrNull() ?: 0,
            amigos = _amigos.value.toIntOrNull() ?: 0
        )

        viewModelScope.launch(Dispatchers.IO) {
            if (repository.atualizarPerfil(perfilAtualizado)) {
                _perfil.value = perfilAtualizado
                _isEditing.value = false
            }
        }
    }

    fun deletarPerfil() {
        viewModelScope.launch(Dispatchers.IO) {
            _perfil.value?.let {
                if (repository.deletarPerfil(it)) {
                    _perfil.value = null
                    _isEditing.value = false
                    _showDeleteDialog.value = false

                    carregarPerfilInicial()
                }
            }
        }
    }

    fun criarNovoPerfil() {
        viewModelScope.launch(Dispatchers.IO) {
            val novoPerfil = repository.salvarPerfil(_nome.value, _email.value)
            if (novoPerfil != null) {
                _perfil.value = novoPerfil
                _showCreateDialog.value = false
            }
        }
    }

    fun onShowDeleteDialog(show: Boolean) { _showDeleteDialog.value = show }
    fun onShowCreateDialog(show: Boolean) {
        _showCreateDialog.value = show

        if(show) {
            _nome.value = ""
            _email.value = ""
        }
    }
}