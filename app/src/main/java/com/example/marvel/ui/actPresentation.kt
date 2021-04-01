package com.example.marvel.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.marvel.R

class actPresentation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_presentation)
        supportActionBar?.hide()
    }

    override fun onStart() {
        super.onStart()

        Handler().postDelayed({
            val intent = Intent(this, actSearchHeros::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}