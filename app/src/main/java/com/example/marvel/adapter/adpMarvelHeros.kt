package com.example.marvel.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel.R
import com.example.marvel.data.HeroSelectedInterface
import com.example.marvel.pojo.HeroDataResult
import com.example.marvel.ui.actHero
import kotlinx.android.synthetic.main.marvel_hero.view.*
import java.io.Serializable

class adpMarvelHeros (
    private val herosDataset: ArrayList<Map<String, Any>>,
    private val context: Context
) : RecyclerView.Adapter<adpMarvelHeros.HerosHolder>(), HeroSelectedInterface {

    override var heroSelected: HeroDataResult = HeroDataResult()

    private var EXTRAS_HERO: String = "HERO"
    var heroData = HeroDataResult()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HerosHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.marvel_hero, parent, false)
        return HerosHolder(view)
    }

    override fun onBindViewHolder(holder: HerosHolder, position: Int) {
        val hero: Map<String, Any> = herosDataset[position]

        holder?.let {
            holder.nameHero.text = hero["name"].toString()
            if (hero["description"].toString() != "") {
                holder.descritption.text = hero["description"].toString()
            }

            val thumbnail: Map<String, Any> = hero["thumbnail"] as Map<String, Any>
            val path: String = thumbnail["path"].toString()
            val extension: String = thumbnail["extension"].toString()
            val url =  "$path.$extension"

            Glide.with(context).load(url).into(holder.iconHero)

            holder.itemView.setOnClickListener {

                heroData.idHero = hero["id"] as Double
                heroData.nameHero = hero["name"] as String
                heroData.descriptionHero = hero["description"] as String
                heroData.recentModified = hero["modified"] as String
                heroData.thumbnail = url
                val resourceComics: Map<String, Any> = hero["comics"] as Map<String, Any>
                heroData.resourceComics = resourceComics["collectionURI"] as String
                val resourceSeries: Map<String, Any> = hero["series"] as Map<String, Any>
                heroData.resourceSeries = resourceSeries["collectionURI"] as String
                val resourceStories: Map<String, Any> = hero["stories"] as Map<String, Any>
                heroData.resourceStories = resourceStories["collectionURI"] as String
                val resourceEvents: Map<String, Any> = hero["events"] as Map<String, Any>
                heroData.resourceEvents = resourceEvents["collectionURI"] as String

                heroSelected = heroData

                val intent = Intent(context, actHero::class.java)
                intent.putExtra(EXTRAS_HERO, heroData as Serializable)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return herosDataset.size
    }

    class HerosHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameHero = view.tvNameHero
        val descritption = view.tvDescriptionHero
        val iconHero = view.imgIconHero
    }
}
