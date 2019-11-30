package br.com.caelum.twittelumappweb.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.fragment.BuscaTweetsFragment
import br.com.caelum.twittelumappweb.fragment.ListaTweetsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_fab.setOnClickListener {

            val intent = Intent(this, TweetActivity::class.java)
            startActivity(intent)

        }

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.item_lista -> {
                    exibe(ListaTweetsFragment())

                    true
                }
                R.id.item_busca -> {
                    exibe(BuscaTweetsFragment())
                    true
                }

                else -> false
            }
        }

        bottom_navigation.selectedItemId = R.id.item_lista


    }

    private fun exibe(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.meio_da_tela, fragment)
        transaction.commit()
    }


}
