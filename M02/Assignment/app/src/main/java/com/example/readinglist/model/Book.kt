package com.example.readinglist.model

import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable
import java.util.*

class Book{

    var title: String = ""
    var reasonToRead: String = ""
    var hasBeenRead: Boolean = false
    var id: String = ""

    constructor(title: String, reasonToRead: String, hasBeenRead: Boolean, id: String){
        this.title = title
        this.reasonToRead = reasonToRead
        this.hasBeenRead = hasBeenRead
        this.id = id
    }

    constructor(itemToCsv: String){
        val params = itemToCsv.split(",")
        for (i in params.indices) {
            val param = params[i]
            when (i) {
                0 -> this.title = param
                1 -> this.reasonToRead = param
                2 -> this.hasBeenRead = param.toBoolean()
                3 -> this.id = param
            }
        }

    }



    constructor(jsonObject: JSONObject) {
        try {
            this.title = jsonObject.getString("title")
        } catch (e: JSONException) {
            this.title = ""
        }

        try {
            this.reasonToRead = jsonObject.getString("reason_to_read")
        } catch (e: JSONException) {
            this.reasonToRead = ""
        }

        try {
            this.hasBeenRead = jsonObject.getBoolean("has_been_read")
        } catch (e: JSONException) {
            this.hasBeenRead = false
        }


        try {
            this.id = jsonObject.getString("id")
        } catch (e: JSONException) {
            this.id = ""
        }


    }

    fun toJsonObject(): JSONObject? {
        try {
            return JSONObject().apply {
                put("title", title)
                put("entry_text", reasonToRead)
                put("hasBeenRead", hasBeenRead)
                put("id", id)
            }} catch (e: JSONException) {
            return try {
                JSONObject("{\"title\" : \"$title\", \"reason_to_read\" : \"$reasonToRead\", \"has_been_read\" : \"$hasBeenRead\", \"id\" : \"$id\"" )
            } catch (e2: JSONException) {
                e2.printStackTrace()
                return null
            }
        }
    }



    fun toCsvString(): String = "${this.title},${this.reasonToRead},${this.hasBeenRead},${this.id}"

}