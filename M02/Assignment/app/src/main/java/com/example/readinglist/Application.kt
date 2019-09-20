/*
package com.example.readinglist

import android.app.Application

val repo: PersistanceInterface by lazy {
    App.repo!!
}

// TODO: 3. Extend Timber to include class, method, line numbers!
*/
/*class MyDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return String.format(
            "[C:%s] [M:%s] [L:%s]",
            super.createStackElementTag(element),
            element.methodName,
            element.lineNumber
        )
    }
}*//*


class App : Application() {

    // TODO: 4. Provide an Application-wide Shared Preferences
    companion object {
        var repo: PersistanceInterface? = null
    }

    override fun onCreate() {
        super.onCreate()

        repo = BookFileStorage(applicationContext)

        // TODO: 2. Configure Timber logging
        // "Timber" Library
        */
/*if (BuildConfig.DEBUG) {
            Timber.plant(MyDebugTree())
        }*//*

    }
}*/
