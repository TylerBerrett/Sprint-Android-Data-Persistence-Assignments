package com.example.readinglist.model

import android.content.Context
import androidx.room.Room
import com.example.learningroom.App
import com.example.readinglist.PersistanceInterface

class DataBaseBuilder(context: Context) : PersistanceInterface {

    override fun getAllBooks(): ArrayList<Book> {
        val list = db.bookDao().getAll()
        return ArrayList(list)
    }

    override fun updateBook(book: Book) {
        db.bookDao().addBook(book)
    }

    override fun getAllBookIds(): ArrayList<String> {
        return ArrayList()
    }

    override fun getBookCsvStringById(id: String): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    val db = Room.databaseBuilder(
        context.applicationContext,
        AppDataBase::class.java,
        "saved-books"
    ).fallbackToDestructiveMigration().build()
}