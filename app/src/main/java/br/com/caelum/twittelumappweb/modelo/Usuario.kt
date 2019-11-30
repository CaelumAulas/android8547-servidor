package br.com.caelum.twittelumappweb.modelo

data class Usuario(val nome: String,
              val username: String,
              val senha: String,
              val foto: String? = null,
              val id: Long = 0)