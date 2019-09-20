package com.example.readinglist.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningroom.App
import com.example.readinglist.*
import com.example.readinglist.SharedPrefsDao.PREFRENCE_ID_LIST
import com.example.readinglist.SharedPrefsDao.PREFRENCE_KEY
import com.example.readinglist.model.Book
import com.example.readinglist.model.DataBaseBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class MainActivity : AppCompatActivity() {

    init {
        BookFileStorage.context = this
        GetListAsyncTask().execute()
    }

    companion object {
        val SEND_ID_KEY = "id key"
        val SEND_BOOK_KEY = "book key"
        val FROM_EDIT_BOOK = "edit key"
        val CREATE_ENTRY_KEY = 0
        val EDIT_ENTRY_KEY = 1

        var preferences: SharedPreferences? = null

        var listOfBooks = arrayListOf<Book>()

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = getSharedPreferences(PREFRENCE_KEY, Context.MODE_PRIVATE)



        val test = listOfBooks
        val i = 0


       //val test = preferences?.getString(PREFRENCE_ID_LIST, "")

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
                    CREATE_ENTRY_KEY -> listOfBooks.add(returnedBook)
                    EDIT_ENTRY_KEY -> listOfBooks[returnedBook.id - 1].title = returnedBook.title
                }


                //SharedPrefsDao.updateBook(Book(csvBook))
                //BookFileStorage.updateBook(Book(csvBook))
                CreateAsyncTask().execute(Book(csvBook))



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
            holder.bookTitle.text = list[position].title + list[position].id

            holder.cardView.setOnClickListener {
                val editIntent = Intent(this@MainActivity, EditBookActivity::class.java)
                editIntent.putExtra(SEND_BOOK_KEY, list[position].toCsvString())
                this@MainActivity.startActivityForResult(editIntent, EDIT_ENTRY_KEY)
            }
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val bookTitle = view.book_title
            val cardView = view.card_view_title
        }

    }

    class CreateAsyncTask: AsyncTask<Book, Void, Unit>(){
        override fun doInBackground(vararg book: Book?) {
            if (book.isNotEmpty()){
                book[0]?.let {
                    App.repo?.updateBook(it)
                }
            }

        }

    }

    class GetListAsyncTask: AsyncTask<Void, Void, ArrayList<Book>>(){
        override fun doInBackground(vararg p0: Void?): ArrayList<Book> {
            return App.repo?.getAllBooks()!!
        }

        override fun onPostExecute(result: ArrayList<Book>?) {
            super.onPostExecute(result)
            result?.forEach {
                listOfBooks.add(it)
            }
        }
    }


}
