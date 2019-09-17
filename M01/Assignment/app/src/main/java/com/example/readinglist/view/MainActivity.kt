package com.example.readinglist.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.readinglist.R
import com.example.readinglist.model.Book
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class MainActivity : AppCompatActivity() {

    companion object {
        val SEND_ID_KEY = "id key"
        val SEND_BOOK_KEY = "book key"
        val FROM_EDIT_BOOK = "edit key"
        val CREATE_ENTRY_KEY = 0
        val EDIT_ENTRY_KEY = 1
        val PREFRENCE_KEY = "save key"
    }

    var preferences: SharedPreferences? = null


    val book = Book("title", "reason", false, "0")
    val book1 = Book("title1", "reason", true, "1")
    val book2 = Book("title2", "reason", false, "2")

    val listOfBooks = arrayListOf(book, book1, book2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        preferences = getSharedPreferences(PREFRENCE_KEY, Context.MODE_PRIVATE)


        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = BookRecyclerView(listOfBooks)

        button_add_book.setOnClickListener {
            val idIntent = Intent(this, EditBookActivity::class.java)
            idIntent.putExtra(SEND_ID_KEY, listOfBooks.count().toString())
            startActivityForResult(idIntent, CREATE_ENTRY_KEY)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            val csvBook = data?.getStringExtra(FROM_EDIT_BOOK)

            if (csvBook != null) {
                val returnedBook = Book(csvBook)
                when (requestCode) {
                    0 -> listOfBooks.add(returnedBook)
                    1 -> listOfBooks[returnedBook.id.toInt()].title = returnedBook.title
                }
            }



            recycler_view.adapter?.notifyDataSetChanged()

        }
    }


    inner class BookRecyclerView(val list: ArrayList<Book>) :
        RecyclerView.Adapter<BookRecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bookTitle.text = list[position].title

            holder.cardView.setOnClickListener {
                val editIntent = Intent(this@MainActivity, EditBookActivity::class.java)
                editIntent.putExtra(SEND_BOOK_KEY, list[position].toCsvString(list[position]))
                this@MainActivity.startActivityForResult(editIntent, EDIT_ENTRY_KEY)
            }
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val bookTitle = view.book_title
            val cardView = view.card_view_title
        }

    }


}
