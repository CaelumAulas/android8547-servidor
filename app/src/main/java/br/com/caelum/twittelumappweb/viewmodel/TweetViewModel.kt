package br.com.caelum.twittelumappweb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.modelo.Usuario

class TweetViewModel(private val repository: TweetRepository) : ViewModel() {


    fun salva(tweet: Tweet) = repository.salva(tweet)

    fun falha() = repository.excecao

    fun novoTweet() = repository.tweetCriado

    fun lista(): LiveData<List<Tweet>> = repository.pegaLista()
    fun carregaTweets() = repository.buscaTweets()
}