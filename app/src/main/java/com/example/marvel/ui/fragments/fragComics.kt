package com.example.marvel.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvel.R
import com.example.marvel.adapter.adpComicsHero
import com.example.marvel.data.HeroSelectedInterface
import com.example.marvel.data.MapLoadData
import com.example.marvel.pojo.HeroDataResult
import com.example.marvel.service.MarvelConnections
import com.example.marvel.utils.MarvelHashCode
import kotlinx.android.synthetic.main.frag_comics.view.*

class fragComics(override var heroSelected: HeroDataResult)
    : Fragment(), HeroSelectedInterface {

    lateinit var mapComics: ArrayList<Map<String, Any>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(
            R.layout.frag_comics,
            container,
            false)

        view.rvComics.adapter = adpComicsHero(mapComics, inflater.context)
        val layoutManager = LinearLayoutManager(inflater.context)
        view.rvComics.layoutManager = layoutManager

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        var resource: String = heroSelected.resourceComics.toString()
        var timestamp: Long = System.currentTimeMillis()
        mapComics = MapLoadData().loadComics(
            MarvelConnections()
            .connComics(
                resource,
                timestamp.toString(),
                MarvelHashCode().marvelHash(timestamp)),
            context)
    }
}