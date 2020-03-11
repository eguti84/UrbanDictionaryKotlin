package com.batch.urbandictionarykotlin.dto

import com.google.gson.annotations.SerializedName

class ListItem {
    @SerializedName("defid")
    var defid = 0
    @SerializedName("sound_urls")
    var soundUrls: List<String>? = null
    @SerializedName("thumbs_down")
    var thumbsDown = 0
    @SerializedName("author")
    var author: String? = null
    @SerializedName("written_on")
    var writtenOn: String? = null
    @SerializedName("definition")
    var definition: String? = null
    @SerializedName("permalink")
    var permalink: String? = null
    @SerializedName("thumbs_up")
    var thumbsUp = 0
    @SerializedName("word")
    var word: String? = null
    @SerializedName("current_vote")
    var currentVote: String? = null
    @SerializedName("example")
    var example: String? = null

    override fun toString(): String {
        return "ListItem{" +
                "defid = '" + defid + '\'' +
                ",sound_urls = '" + soundUrls + '\'' +
                ",thumbs_down = '" + thumbsDown + '\'' +
                ",author = '" + author + '\'' +
                ",written_on = '" + writtenOn + '\'' +
                ",definition = '" + definition + '\'' +
                ",permalink = '" + permalink + '\'' +
                ",thumbs_up = '" + thumbsUp + '\'' +
                ",word = '" + word + '\'' +
                ",current_vote = '" + currentVote + '\'' +
                ",example = '" + example + '\'' +
                "}"
    }
}