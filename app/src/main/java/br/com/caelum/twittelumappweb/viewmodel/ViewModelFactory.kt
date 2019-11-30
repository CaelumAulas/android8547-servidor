package br.com.caelum.twittelumappweb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import br.com.caelum.twittelumappweb.webservices.TweetWebClient
import br.com.caelum.twittelumappweb.websevices.InicializadorDeRetrofit
import br.com.caelum.twittelumappweb.websevices.UsuarioWebClient

object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        TweetViewModel::class.java -> {
            TweetViewModel(Injetor.getTweetRepository) as T
        }
        else -> {
            UsuarioViewModel(Injetor.getUsuarioRepository) as T
        }
    }
}

object Injetor {

    private val getRetrofit = InicializadorDeRetrofit.retrofit

    private val getTweetWebClient = TweetWebClient(getRetrofit)
    val getTweetRepository = TweetRepository(getTweetWebClient)

    private val getUsuarioWebClient = UsuarioWebClient(getRetrofit)
    val getUsuarioRepository = UsuarioRepository(getUsuarioWebClient)
}
