package com.example.marvel.pojo

import java.io.Serializable

class HeroDataResult : Serializable {
    var idHero: Double? = 0.0
    var nameHero: String? = null
    var descriptionHero: String? = null
    var recentModified: String? = null
    var thumbnail: String? = null
    var resourceComics: String? = null
    var resourceSeries: String? = null
    var resourceStories: String? = null
    var resourceEvents: String? = null
}