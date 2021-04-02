package com.example.marvel.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvel.R
import com.example.marvel.adapter.adpMarvelHeros
import com.example.marvel.data.MapLoadHeros
import com.example.marvel.service.InternetConnection
import com.example.marvel.service.MarvelConnections
import com.example.marvel.utils.MarvelHashCode
import kotlinx.android.synthetic.main.act_search_heros.*
import kotlinx.android.synthetic.main.marvel_toolbar.*

class actSearchHeros : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_search_heros)
        supportActionBar?.hide()

        tvSubtitleToolbarDefault.visibility = View.GONE

        var timestamp: Long = System.currentTimeMillis()
        if (savedInstanceState == null) {
            imvSearchHeroByName.setOnClickListener {
                if (InternetConnection().internetOnline(this)) {
                    loadRecyclerView(
                        MapLoadHeros().loadHeros(
                            MarvelConnections()
                                .connCharactereByName(
                                    edtSearchNameHero.text.toString(),
                                    timestamp.toString(),
                                    MarvelHashCode().marvelHash(timestamp)
                                ),
                            this
                        )
                    )
                } else {
                    Toast.makeText(
                        this,
                        "Não há conecxão com a internet!",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        }

        if (InternetConnection().internetOnline(this)) {
            loadRecyclerView(
                MapLoadHeros().loadHeros(
                    MarvelConnections().connCharactereByName(
                        getString(R.string.my_prefer_hero),
                        timestamp.toString(),
                        MarvelHashCode().marvelHash(timestamp)
                    ),
                    this
                )
            )
        } else {
                Toast.makeText(
                    this,
                    "Não há conecxão com a internet!",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
    }

    fun loadRecyclerView(heros: ArrayList<Map<String, Any>>) {
        if (heros.size == 0) {
            Toast.makeText(this, "Hero not found!", Toast.LENGTH_LONG).show()
            return
        }
        rvHeros.adapter = adpMarvelHeros(heros, this)
        val layoutManager = LinearLayoutManager(this)
        rvHeros.layoutManager = layoutManager
    }
}