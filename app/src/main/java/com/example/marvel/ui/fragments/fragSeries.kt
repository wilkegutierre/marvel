package com.example.marvel.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvel.R
import com.example.marvel.adapter.adpSeriesHero

import com.example.marvel.data.HeroSelectedInterface
import com.example.marvel.data.MapLoadData
import com.example.marvel.pojo.HeroDataResult
import com.example.marvel.service.MarvelConnections
import com.example.marvel.utils.MarvelHashCode
import kotlinx.android.synthetic.main.frag_comics.view.*
import kotlinx.android.synthetic.main.frag_series.view.*

class fragSeries(override var heroSelected: HeroDataResult)
    : Fragment(), HeroSelectedInterface {

    lateinit var mapSeries: ArrayList<Map<String, Any>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(
            R.layout.frag_series,
            container,
            false
        )

        view.rvSeries.adapter = adpSeriesHero(mapSeries, inflater.context)
        val layoutManager = LinearLayoutManager(inflater.context)
        view.rvSeries.layoutManager = layoutManager

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        var resource: String = heroSelected.resourceSeries.toString()
        var timestamp: Long = System.currentTimeMillis()
        mapSeries = MapLoadData().loadComics(
            MarvelConnections()
                .connSeries(
                    resource,
                    timestamp.toString(),
                    MarvelHashCode().marvelHash(timestamp)
                ),
            context
        )
    }
}