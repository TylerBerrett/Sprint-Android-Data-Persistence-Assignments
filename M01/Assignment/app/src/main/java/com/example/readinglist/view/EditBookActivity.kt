package com.example.readinglist.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.readinglist.R
import com.example.readinglist.model.Book
import kotlinx.android.synthetic.main.activity_edit_book.*

class EditBookActivity : AppCompatActivity() {

    private var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)

        val makeId = intent.getStringExtra(MainActivity.SEND_ID_KEY)

        val book = intent.getStringExtra(MainActivity.SEND_BOOK_KEY)

        if (book != null){
            val csvToBook = Book(book)
            id = csvToBook.id
            edit_title.setText(csvToBook.title)
            edit_reason.setText(csvToBook.reasonToRead)
            if (csvToBook.hasBeenRead) check_is_read.toggle()
        } else{
            id = makeId
        }



        button_submit.setOnClickListener {
            returnData()
        }

        button_cancel.setOnClickListener {
            val backIntent = Intent()
            setResult(Activity.RESULT_CANCELED, backIntent)
            finish()
        }



    }


    override fun onBackPressed() {
        super.onBackPressed()
        returnData()
    }

    fun returnData(){
        val title = edit_title.text.toString()
        val reason = edit_reason.text.toString()
        var isRead = false
        if (check_is_read.isChecked) isRead = true
        val id = id
        val bookReturn = Book(title, reason, isRead, id)
        val bookReturnCsv = bookReturn.toCsvString()
        val backIntent = Intent()
        backIntent.putExtra(MainActivity.FROM_EDIT_BOOK, bookReturnCsv)
        setResult(Activity.RESULT_OK, backIntent)
        finish()


    }

}
