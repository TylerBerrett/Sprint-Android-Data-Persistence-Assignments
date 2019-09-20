package com.example.learningroom

import android.app.Application
import com.example.readinglist.PersistanceInterface
import com.example.readinglist.model.AppDataBase
import com.example.readinglist.model.DataBaseBuilder

class App : Application() {

    companion object {
        var repo: PersistanceInterface? = null
    }

    override fun onCreate() {
        super.onCreate()

        repo = DataBaseBuilder(applicationContext)

    }


}