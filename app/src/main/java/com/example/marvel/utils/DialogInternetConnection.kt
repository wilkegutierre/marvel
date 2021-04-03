package com.example.marvel.utils

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.example.marvel.R

class DialogInternetConnection {

    fun internetNotFound(context: Context, title: String, subtitle: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(subtitle)

        builder.setPositiveButton(
            context.getString(
                R.string.confirm_internet_not_found)) {
                dialog, which ->
        }
        builder.show()
    }
}