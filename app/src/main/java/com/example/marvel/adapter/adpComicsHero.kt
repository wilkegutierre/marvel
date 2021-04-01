package com.example.marvel.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel.R
import com.example.marvel.dialog.CustomDialog
import com.example.marvel.pojo.HeroDataResult
import com.example.marvel.ui.actHero
import kotlinx.android.synthetic.main.comics_hero.view.*
import kotlinx.android.synthetic.main.marvel_hero.view.*
import java.io.Serializable

class adpComicsHero(
    private val comicsDataset: ArrayList<Map<String, Any>>,
    private val context: Context
) : RecyclerView.Adapter<adpComicsHero.HerosHolder>() {

    private var titleToolbar: String = "Comic"
    var heroData = HeroDataResult()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HerosHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.comics_hero,
            parent,
            false
        )
        return HerosHolder(view)
    }

    override fun onBindViewHolder(holder: HerosHolder, position: Int) {
        val comicsMap: Map<String, Any> = comicsDataset[position]

        val thumbnail: Map<String, Any> = comicsMap["thumbnail"] as Map<String, Any>
        val path: String = thumbnail["path"].toString()
        val extension: String = thumbnail["extension"].toString()
        val url = "$path.$extension"

        var title: String  = comicsMap["title"].toString()
        var description: String =
            if (comicsMap["description"].toString() != "null")
                comicsMap["description"].toString() else {
                context.getString(R.string.comic_description_not_existe)
            }

        Glide.with(context).load(url).into(holder.iconComics)

        holder.nameComic.text = title
        holder.description.text = description

        holder.itemView.setOnClickListener {
            CustomDialog(
                url,
                title,
                description,
                titleToolbar
            ).dialog(context)
        }
    }

    override fun getItemCount(): Int {
        return comicsDataset.size
    }

    class HerosHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameComic = view.tvTitleComicHero
        val description = view.tvDescriptionComicHero
        val iconComics = view.imgComicHero
    }
}
