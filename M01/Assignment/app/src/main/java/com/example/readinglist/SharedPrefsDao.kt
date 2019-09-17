package com.example.readinglist

import android.content.Context
import com.example.readinglist.model.Book
import com.example.readinglist.view.MainActivity
import java.lang.StringBuilder

object SharedPrefsDao {

    val PREFRENCE_KEY = "prefrence"

    val PREFRENCE_ID_LIST = "list of id"
    val PREFRENCE_CREATE = "create"
    val PREFRENCE_NEXT_ID = "id next"



    fun getAllBookIds(): ArrayList<String>{
        val savedIds= MainActivity.preferences?.getString(PREFRENCE_ID_LIST, "")
        val oldIds = savedIds!!.split(",")

        val ids = ArrayList<String>(oldIds.size)

        if (savedIds.isNotBlank()) {
            ids.addAll(oldIds)
        }
        return ids
    }


    fun getNextId(): String{
        return MainActivity.preferences?.getString(PREFRENCE_NEXT_ID, "default") ?: "null"
    }

    fun getBookCsvStringById(id: String){
        //MainActivity().preferences?.getString
        /*return if (listOfBooks.isNotEmpty()){
            val book = listOfBooks[id.toInt()]
            book.toCsvString(book)
        } else ""*/
    }

    fun updateBook (book: Book){
        val ids = getAllBookIds()

        if (ids?.contains(book.id)){
            val editor = MainActivity.preferences?.edit()

            var nextId = MainActivity.preferences?.getString(PREFRENCE_NEXT_ID, "")
            book.id = nextId!!
            var newNextId = nextId!!.toInt()
            editor?.putInt(PREFRENCE_NEXT_ID, ++newNextId)




        }
    }
}