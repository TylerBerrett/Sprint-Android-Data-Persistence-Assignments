package com.example.learningroom

import android.content.Context
import androidx.room.Room

class DataBase(context: Context) {

    val db = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java, "database-name"
    ).fallbackToDestructiveMigration().build()



}