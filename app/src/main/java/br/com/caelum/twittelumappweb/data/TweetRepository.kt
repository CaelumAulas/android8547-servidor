package br.com.caelum.twittelumappweb.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.webservices.TweetWebClient

class TweetRepository(private val client: TweetWebClient) {

    val excecao: MutableLiveData<Throwable> = MutableLiveData()
    val tweetCriado: MutableLiveData<Tweet> = MutableLiveData()
    val lista: MutableLiveData<List<Tweet>> = MutableLiveData()


    fun salva(tweet: Tweet) = client.insere(tweet, sucesso(), erro())

    private fun erro() = { erro: Throwable ->
        excecao.value = erro
    }

    private fun sucesso() = { tweet: Tweet ->
        tweetCriado.value = tweet
    }

    fun pegaLista(): LiveData<List<Tweet>>  = lista

    fun buscaTweets() = client.busca(sucessoPraLista(), erro())

    private fun sucessoPraLista() = { tweets: List<Tweet> ->
        lista.postValue(tweets)
    }


}