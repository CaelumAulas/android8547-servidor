package br.com.caelum.twittelumappweb.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.modelo.Usuario
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.UsuarioViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val usuarioViewModel: UsuarioViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory).get(UsuarioViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_criar.setOnClickListener { usuarioViewModel.cria(pegaUsuarioDaTela()) }
        login_entrar.setOnClickListener { usuarioViewModel.loga(pegaUsuarioDaTela()) }

        usuarioViewModel.estaLogado().observe(this, Observer {estaLogado ->
            if(estaLogado) {
                vaiPraTelaPrincipal()
            }
        })

        usuarioViewModel.falha().observe(this, Observer {
            Toast.makeText(this, it?.message, Toast.LENGTH_LONG).show()
            Log.i("Login", "falha ao logar", it)
        })

    }

    private fun vaiPraTelaPrincipal() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun pegaUsuarioDaTela(): Usuario {
        val nome = login_campoNome.text.toString()
        val username = login_campoUsername.text.toString()
        val senha = login_campoSenha.text.toString()

        return Usuario(nome, username, senha)
    }
}
