package com.batch.urbandictionarykotlin.dto

class UrbanResponse {
    var list: List<Definition>? = null

    override fun toString(): String {
        return "UrbanResponse{" +
                "list = '" + list + '\'' +
                "}"
    }
}