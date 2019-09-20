package com.example.readinglist.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.readinglist.room.BookDao

@Database(entities = arrayOf(Book::class), version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase(){
    abstract fun bookDao(): BookDao
}