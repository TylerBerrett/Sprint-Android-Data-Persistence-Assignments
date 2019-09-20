package com.lambdaschool.sharedprefs

import android.app.Application
import com.lambdaschool.sharedprefs.ui.JournalFileRepo
import timber.log.Timber

<<<<<<< HEAD
// TODO: 5. Lazy initialization of a repo object for Activities to use...
=======
// TODO 4: Change to the repo interface here
>>>>>>> 99aa2683da55bd186f4a7ef1f2648d71cd172c26
val repo: JournalRepoInterface by lazy {
    App.repo!!
}

class MyDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return String.format(
            "[C:%s] [M:%s] [L:%s]",
            super.createStackElementTag(element),
            element.methodName,
            element.lineNumber
        )
    }
}

class App : Application() {

    companion object {
        var repo: JournalRepoInterface? = null
    }

    override fun onCreate() {
        super.onCreate()

<<<<<<< HEAD
=======
        // TODO 5: Instantiate the File repo here instead of Prefs
        //repo = Prefs(applicationContext)
>>>>>>> 99aa2683da55bd186f4a7ef1f2648d71cd172c26
        repo = JournalFileRepo(applicationContext)

        // "Timber" Library
        if (BuildConfig.DEBUG) {
            Timber.plant(MyDebugTree())
        }
    }
}