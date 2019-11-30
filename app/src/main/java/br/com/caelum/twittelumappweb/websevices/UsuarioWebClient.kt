package br.com.caelum.twittelumappweb.websevices

import android.util.Log
import br.com.caelum.twittelumappweb.modelo.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

class UsuarioWebClient(private val retrofit: Retrofit) {
    private val usuarioService = retrofit.create(UsuarioService::class.java)

    fun cria(usuario: Usuario, sucesso: (Usuario) -> Unit, falha: (Throwable) -> Unit) {

        val chamadaPraCriar = usuarioService.cadastra(usuario)
        chamadaPraCriar.enqueue(object : Callback<Usuario> {
            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                falha(t)
            }

            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (!response.isSuccessful) {
                    Log.e("usuario", "${response.body()}")
                }

                val usuarioLogado = response.body()
                usuarioLogado?.let(sucesso)
            }
        })
    }

    fun fazLogin(usuario: Usuario, sucesso: (Usuario) -> Unit, falha: (Throwable) -> Unit) {
        val chamadaPraLogar = usuarioService.loga(usuario)

        chamadaPraLogar.enqueue(object : Callback<Usuario> {
            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                falha(t)
            }

            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                response.body()?.let(sucesso)
            }
        })

    }
}

private interface UsuarioService {
    @POST("/usuario")
    fun cadastra(@Body usuario: Usuario): Call<Usuario>

    @POST("/usuario/login")
    fun loga(@Body usuario: Usuario): Call<Usuario>

}