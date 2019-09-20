package com.example.readinglist.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.readinglist.model.Book

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun getAll(): List<Book>

    /*@Query("SELECT id FROM book")
    fun getAllIds(): ArrayList<String>*/

    @Insert
    fun addBook(book: Book)

    @Delete
    fun deleteBook(book: Book)
}