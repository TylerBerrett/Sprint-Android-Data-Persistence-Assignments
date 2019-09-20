package com.example.readinglist.room

import androidx.room.*
import com.example.readinglist.model.Book

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun getAll(): List<Book>

    /*@Query("SELECT id FROM book")
    fun getAllIds(): ArrayList<String>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBook(book: Book)

    @Delete
    fun deleteBook(book: Book)
}