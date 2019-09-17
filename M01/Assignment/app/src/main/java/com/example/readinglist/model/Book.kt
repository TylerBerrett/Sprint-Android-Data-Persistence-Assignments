package com.example.readinglist.model

import java.io.Serializable

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


    fun toCsvString(book: Book): String = "${book.title},${book.reasonToRead},${book.hasBeenRead},${book.id}"

}