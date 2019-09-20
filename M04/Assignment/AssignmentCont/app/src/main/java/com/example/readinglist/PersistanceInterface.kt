package com.example.readinglist

import com.example.readinglist.model.Book

interface PersistanceInterface {

    fun getAllBookIds(): ArrayList<String>
    fun getAllBooks(): ArrayList<Book>
    fun getBookCsvStringById(id: String): String?
    fun updateBook (book: Book)

}