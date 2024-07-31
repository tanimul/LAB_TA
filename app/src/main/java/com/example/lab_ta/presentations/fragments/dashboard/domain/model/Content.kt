package com.example.lab_ta.presentations.fragments.dashboard.domain.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Contents(

    @SerializedName("score") var score: Double? = null,
    @SerializedName("show") var content: Content? = Content()

)

@Keep
data class Content(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("language") var language: String? = null,
    @SerializedName("genres") var genres: ArrayList<String> = arrayListOf(),
    @SerializedName("status") var status: String? = null,
    @SerializedName("runtime") var runtime: Int? = null,
    @SerializedName("averageRuntime") var averageRuntime: Int? = null,
    @SerializedName("premiered") var premiered: String? = null,
    @SerializedName("ended") var ended: String? = null,
    @SerializedName("officialSite") var officialSite: String? = null,
    @SerializedName("schedule") var schedule: Schedule? = Schedule(),
    @SerializedName("rating") var rating: Rating? = Rating(),
    @SerializedName("weight") var weight: Int? = null,
    @SerializedName("network") var network: Network? = Network(),
    @SerializedName("webChannel") var webChannel: String? = null,
    @SerializedName("dvdCountry") var dvdCountry: String? = null,
    @SerializedName("externals") var externals: Externals? = Externals(),
    @SerializedName("image") var image: Image? = Image(),
    @SerializedName("summary") var summary: String? = null,
    @SerializedName("updated") var updated: Int? = null,
    @SerializedName("_links") var Links: Links? = Links()

)

@Keep
data class Schedule(

    @SerializedName("time") var time: String? = null,
    @SerializedName("days") var days: ArrayList<String> = arrayListOf()

)

@Keep
data class Rating(

    @SerializedName("average") var average: Double? = null

)

@Keep
data class Country(

    @SerializedName("name") var name: String? = null,
    @SerializedName("code") var code: String? = null,
    @SerializedName("timezone") var timezone: String? = null

)

@Keep
data class Network(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("country") var country: Country? = Country(),
    @SerializedName("officialSite") var officialSite: String? = null

)

@Keep
data class Externals(

    @SerializedName("tvrage") var tvrage: String? = null,
    @SerializedName("thetvdb") var thetvdb: Int? = null,
    @SerializedName("imdb") var imdb: String? = null

)

@Keep
data class Image(

    @SerializedName("medium") var medium: String? = null,
    @SerializedName("original") var original: String? = null

)

@Keep
data class Links(

    @SerializedName("self") var self: Self? = Self(),
    @SerializedName("previousepisode") var previousepisode: Previousepisode? = Previousepisode()

)

@Keep
data class Previousepisode(

    @SerializedName("href") var href: String? = null,
    @SerializedName("name") var name: String? = null

)

@Keep
data class Self(

    @SerializedName("href") var href: String? = null

)