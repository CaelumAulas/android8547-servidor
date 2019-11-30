package br.com.caelum.twittelumappweb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.data.UsuarioRepository

object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    private fun repository() = TweetRepository()
    private fun usuarioRepository() = UsuarioRepository()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass) {
        TweetViewModel::class.java ->
            TweetViewModel(repository()) as T
        else ->
            UsuarioViewModel(usuarioRepository()) as T
    }

}