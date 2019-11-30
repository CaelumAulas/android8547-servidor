package br.com.caelum.twittelumappweb.fragment

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.adapter.TweetAdapter
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_lista_tweets.*

class BuscaTweetsFragment : Fragment() {
    val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(activity!!, ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lista_tweets, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.busca_menu, menu)

        val itemLupa = menu?.findItem(R.id.barra_acoes_busca)
        val searchView = itemLupa?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(texto: String?): Boolean {
                val tweets = viewModel.lista().value

                texto?.let {
                    val filtrados = tweets?.filter { tweet -> tweet.mensagem.contains(texto, true) }
                    lista_tweets.adapter = TweetAdapter(filtrados!!)
                }

                return true // já tratamos a lógica
            }

            override fun onQueryTextChange(texto: String?): Boolean {
                return true
            }
        })
    }
}