package com.example.readinglist

import android.content.Context
import com.example.readinglist.model.Book
import com.example.readinglist.view.MainActivity
import java.lang.StringBuilder



object SharedPrefsDao: PersistanceInterface {

    val PREFRENCE_KEY = "prefrence"

    val PREFRENCE_ID_LIST = "list of id"
    val PREFRENCE_CREATE = "Book#"
    val PREFRENCE_NEXT_ID = "id next"



    override fun getAllBookIds(): ArrayList<String>{
        val savedIds= MainActivity.preferences?.getString(PREFRENCE_ID_LIST, "")
        val oldIds = savedIds!!.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        val ids = ArrayList<String>(oldIds.size)

        if (savedIds.isNotBlank()) {
            ids.addAll(oldIds)
        }
        return ids
    }

    override fun getAllBooks(): ArrayList<Book>{
        val books = ArrayList<Book>()
        getAllBookIds().forEach {
            books.add(Book(getBookCsvStringById(it)!!))
        }
        return books
    }


    fun getNextId(): String{
        return MainActivity.preferences?.getString(PREFRENCE_NEXT_ID, "default") ?: "null"
    }

    override fun getBookCsvStringById(id: String): String? {
        val cvs = MainActivity.preferences?.getString(PREFRENCE_CREATE + id, "")
        return cvs
    }


    override fun updateBook (book: Book){
        val newIds = getAllBookIds()
        val editor = MainActivity.preferences?.edit()
        if (!newIds.contains(book.id.toString())){
            newIds.add(book.id.toString())

            val stringBuilder = StringBuilder()
            newIds.forEach {
                stringBuilder.append(it).append(",")
            }
            editor?.putString(PREFRENCE_ID_LIST, stringBuilder.toString())
            editor?.putString(PREFRENCE_CREATE + book.id, book.toCsvString())
            editor?.commit()
        } else {
            editor?.putString(PREFRENCE_CREATE + book.id, book.toCsvString())
            editor?.commit()
        }








    }
}