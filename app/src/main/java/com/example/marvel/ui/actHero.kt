package com.example.marvel.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.marvel.R
import com.example.marvel.adapter.adpHeroFragment
import com.example.marvel.pojo.HeroDataResult
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.act_hero.*
import kotlinx.android.synthetic.main.marvel_toolbar.*

class actHero : AppCompatActivity() {

    var hero = HeroDataResult()

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_hero)
        supportActionBar?.hide()

        if (savedInstanceState == null) {
            hero = intent.extras?.get("HERO") as HeroDataResult
            tvTitleToolbarDefault.text = hero.nameHero
            tvSubtitleToolbarDefault.visibility = View.GONE
            tabHero()
        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun tabHero() {
        tabHero.addTab(
            tabHero.newTab().setIcon(
                resources.getDrawable(R.drawable.superhero_icon, null))
        )
        tabHero.addTab(
            tabHero.newTab().setIcon(
                resources.getDrawable(R.drawable.comics_icon, null))
        )
        tabHero.addTab(
            tabHero.newTab().setIcon(
                resources.getDrawable(R.drawable.series_icon, null))
        )

        tabHero.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = adpHeroFragment(
            this,
            supportFragmentManager,
            tabHero.tabCount,
            hero
        )

        pageHero.adapter = adapter
        pageHero.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tabHero))
        tabHero.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pageHero.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}
