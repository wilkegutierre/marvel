package com.example.marvel.adapter

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.marvel.pojo.HeroDataResult
import com.example.marvel.ui.fragments.*
import java.io.Serializable

internal class adpHeroFragment(
    var context: Context,
    var fragment: FragmentManager,
    var tabs: Int,
    var hero: HeroDataResult
) : FragmentPagerAdapter(
    fragment,
    FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                fragHero(hero)
            }
            1 -> {
                fragComics(hero)
            }
            2 -> {
                fragSeries(hero)
            }
            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return tabs
    }
}