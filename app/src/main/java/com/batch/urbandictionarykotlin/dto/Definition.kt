package com.batch.urbandictionarykotlin.dto

import com.google.gson.annotations.SerializedName
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Definition {
    var defid = 0
    @SerializedName("sound_urls")
    var soundUrls: List<String>? = null

    @SerializedName("thumbs_down")
    var thumbsDown:Int?=null

    @SerializedName("thumbs_up")
    var thumbsUp:Int?=null

    var author: String? = null

    @SerializedName("written_on")
    private var writtenOn: String? = null

    var definition: String? = null

    var permalink: String? = null

    var word: String? = null

    @SerializedName("current_vote")
    var currentVote: String? = null

    var example: String? = null

    fun getWrittenOn(): String {
        val format = SimpleDateFormat("yyyy-MM-dd")
        var date: Date? = Date()
        try {
            date = format.parse(writtenOn)
        } catch (e: ParseException) { // TODO Auto-generated catch block
            e.printStackTrace()
        }
        val df = DateFormat.getDateInstance(
            DateFormat.DEFAULT,
            Locale.getDefault()
        )
        return df.format(date)
    }

    override fun toString(): String {
        return "Definition{" +
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