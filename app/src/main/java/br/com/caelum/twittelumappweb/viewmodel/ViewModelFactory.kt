package br.com.caelum.twittelumappweb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import br.com.caelum.twittelumappweb.websevices.InicializadorDeRetrofit
import br.com.caelum.twittelumappweb.websevices.UsuarioWebClient

object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    private fun repository() = TweetRepository()
    private fun usuarioRepository(): UsuarioRepository {
        val retrofit = InicializadorDeRetrofit.retrofit
        val usuarioWebClient = UsuarioWebClient(retrofit)
        return UsuarioRepository(usuarioWebClient)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass) {
        TweetViewModel::class.java ->
            TweetViewModel(repository()) as T
        else ->
            UsuarioViewModel(usuarioRepository()) as T
    }

}