package br.com.caelum.twittelumappweb.viewmodel

import androidx.lifecycle.ViewModel
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import br.com.caelum.twittelumappweb.modelo.Usuario

class UsuarioViewModel(private val usuarioRepository: UsuarioRepository) : ViewModel() {
    fun cria(usuario: Usuario) = usuarioRepository.cria(usuario)
    fun loga(usuario: Usuario) = usuarioRepository.loga(usuario)
}