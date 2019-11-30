package br.com.caelum.twittelumappweb.webservices

import br.com.caelum.twittelumappweb.modelo.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class TweetWebClient(private val retrofit: Retrofit) {
    private val tweetService = retrofit.create(TweetService::class.java)

    fun insere(
            tweet: Tweet,
            sucesso: (tweet: Tweet) -> Unit,
            falha: (erro: Throwable) -> Unit
    ) {

        tweetService.salva(tweet).enqueue(object : Callback<Tweet> {
            override fun onFailure(call: Call<Tweet>, t: Throwable) {
                falha(t)
            }

            override fun onResponse(call: Call<Tweet>, response: Response<Tweet>) {
                response.body()?.let(sucesso)
            }

        })
    }

    fun busca(sucessoPraLista: (List<Tweet>) -> Unit, erro: (Throwable) -> Unit) {
        tweetService.lista().enqueue(object : Callback<List<Tweet>> {
            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                erro(t)
            }

            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                response.body()?.let(sucessoPraLista)
            }
        })
    }

    private interface TweetService {
        @POST("/tweet")
        fun salva(@Body tweet: Tweet): Call<Tweet>

        @GET("/tweet")
        fun lista(): Call<List<Tweet>>
    }
}
