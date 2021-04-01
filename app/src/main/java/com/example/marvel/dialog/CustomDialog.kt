package com.example.marvel.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.example.marvel.R
import kotlinx.android.synthetic.main.custom_dialog.view.*
import kotlinx.android.synthetic.main.frag_hero.view.*
import kotlinx.android.synthetic.main.marvel_toolbar.view.*

class CustomDialog(
    var urlImage: String,
    var name: String,
    var description: String,
    var titleToolbar: String) {


    fun dialog(context: Context){
        val view = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null)

        val mBuilder = AlertDialog.Builder(context)
            .setView(view)

        val  mAlertDialog = mBuilder.show()

        view.tvTitleToolbarDefault.text = titleToolbar
        view.tvSubtitleToolbarDefault.text = name
        Glide.with(context).load(urlImage).into(view.imgItemDialog)
        view.tvDescriptionItemDialog.text = description
    }
}