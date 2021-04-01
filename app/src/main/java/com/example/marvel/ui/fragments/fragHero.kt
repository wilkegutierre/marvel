package com.example.marvel.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.marvel.R
import com.example.marvel.data.HeroSelectedInterface
import com.example.marvel.data.heroInterface
import com.example.marvel.pojo.HeroDataResult
import kotlinx.android.synthetic.main.frag_hero.*
import kotlinx.android.synthetic.main.frag_hero.view.*
import java.lang.Exception

class fragHero(override var heroSelected: HeroDataResult)
    : Fragment(), HeroSelectedInterface {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View = inflater.inflate(
            R.layout.frag_hero,
            container,
            false)

        try {
            Glide.with(this)
                .load(heroSelected.thumbnail)
                .into(view.imgHeroSelected)

            if (heroSelected.descriptionHero != "") {
                view.tvDescriptionHeroSelected.text = heroSelected.descriptionHero
            }
        } catch (ex: Exception) {
            Log.e("Log", ex.toString())
        }
        // Inflate the layout for this fragment
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        var hero: HeroDataResult = heroSelected
    }
}