package br.com.caelum.twittelumappweb.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.caelum.twittelumappweb.modelo.Usuario
import br.com.caelum.twittelumappweb.websevices.UsuarioWebClient

class UsuarioRepository(private val usuarioWebClient: UsuarioWebClient) {
    val usuarioEstaLogado: MutableLiveData<Boolean> = MutableLiveData()
    val erro: MutableLiveData<Throwable> = MutableLiveData()
    val usuarioDaSessao: MutableLiveData<Usuario> = MutableLiveData()

    fun cria(usuario: Usuario) {
        usuarioWebClient.cria(usuario, sucesso, falha)
        Log.i("usuario", "$usuario criado")
    }

    fun loga(usuario: Usuario) {
        usuarioWebClient.fazLogin(usuario, sucesso, falha)
        Log.i("usuario", "$usuario logado")
    }

    private val sucesso = fun (usuario: Usuario) {
        usuarioEstaLogado.value = true
        usuarioDaSessao.value = usuario
    }

    private val falha = fun (excecao: Throwable) {
        usuarioEstaLogado.value = false
        erro.value = excecao
    }
}