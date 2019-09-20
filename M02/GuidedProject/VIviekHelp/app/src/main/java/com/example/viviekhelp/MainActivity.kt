package com.example.viviekhelp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefrences = getSharedPreferences("prefs", Context.MODE_PRIVATE)

        if (prefrences.getString("input", "") != ""){
            edit_text.setText(prefrences.getString("input", ""))
        }

        button.setOnClickListener {
            val editor = prefrences.edit()
            editor.putString("input", edit_text.text.toString())
            editor.commit()
        }


    }
}
