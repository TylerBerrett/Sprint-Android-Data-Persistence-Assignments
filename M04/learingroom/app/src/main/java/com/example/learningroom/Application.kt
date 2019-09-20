package com.example.learningroom

import android.app.Application

class App : Application() {

    companion object {
        var repo: DataBase? = null
    }

    override fun onCreate() {
        super.onCreate()

        repo = DataBase(applicationContext)

    }


}