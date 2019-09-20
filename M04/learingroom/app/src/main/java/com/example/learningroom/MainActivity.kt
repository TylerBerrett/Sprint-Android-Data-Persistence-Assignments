package com.example.learningroom

import android.app.Application
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {

        var text: String? = null
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userOne = User(null, "Tyler", "Berrett")


        if (text != null){
            text_view.text = text
        }


        CreateAsyncTask().execute(userOne)






    }


    class CreateAsyncTask: AsyncTask<User, Void, Unit>(){
        override fun doInBackground(vararg user: User?) {
            if (user.isNotEmpty()){
                user[0]?.let {
                    App.repo?.db?.userDao()?.insertUser(it)
                }
            }
            val test = App.repo?.db?.userDao()?.getAll()
            val i = 0

        }

    }




}
