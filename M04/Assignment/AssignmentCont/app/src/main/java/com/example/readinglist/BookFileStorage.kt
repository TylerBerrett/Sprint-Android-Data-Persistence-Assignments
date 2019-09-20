package com.example.readinglist

import android.annotation.SuppressLint
import android.content.Context
import android.os.Environment
import com.example.readinglist.model.Book
import org.json.JSONException
import org.json.JSONObject
import java.io.*

@SuppressLint("StaticFieldLeak")
object BookFileStorage: PersistanceInterface {

    var context: Context? = null


    override fun getAllBookIds(): ArrayList<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    override fun getBookCsvStringById(id: String): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateBook(book: Book) {
        val bookString = book.toJsonObject()
        val filename = "book${book.id}.json"
        writeToFile(filename, bookString.toString())
    }


    private fun writeToFile(filename: String, entryString: String){
        val dir = storageDirectory
        val outputFile = File(dir, filename)

        //Open FileWriter
        var writer: FileWriter? = null
        try {
            //Write
            writer = FileWriter(outputFile)
            writer.write(entryString)
        } catch (e: IOException){
            e.printStackTrace()
        } finally {
            if (writer != null) {
                try {
                    //Close
                    writer.close()
                } catch (e2: IOException){
                    e2.printStackTrace()
                }
            }
        }
    }

    val storageDirectory: File
        get() {
            if (isExternalStorageWriteable) {
                val directory = context!!.filesDir
                return if (!directory.exists() && !directory.mkdirs()) {
                    context!!.cacheDir
                } else {
                    directory
                }
            } else {
                return context!!.cacheDir
            }
        }


    val isExternalStorageWriteable: Boolean
        get() {
            val state = Environment.getExternalStorageState()
            return state == Environment.MEDIA_MOUNTED
        }


    override fun getAllBooks(): ArrayList<Book> {
        val books = ArrayList<Book>()

        for (filename in filelist){
            val json = readFromFile(filename)
            try {
                books.add(Book(JSONObject(json)))
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return books
    }


    val filelist: java.util.ArrayList<String>
        get() {
            val fileNames = arrayListOf<String>()
            val dir = storageDirectory

            val list = dir.list()
            if (list != null) {
                for (name in list) {
                    if (name.contains(".json")) {
                        fileNames.add(name)
                    }
                }
            }
            return fileNames
        }
    private fun readFromFile(filename: String): String {
        val inputFile = File(storageDirectory, filename)
        var readString: String? = null
        var reader: FileReader? = null
        try {
            reader = FileReader(inputFile)
            readString = reader.readText()
        } catch (e: FileNotFoundException){
            e.printStackTrace()
        } catch (e: IOException){
            e.printStackTrace()
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException){
                    e.printStackTrace()
                }
            }
        }
        // return readData.toString()
        return readString ?: ""
    }








}