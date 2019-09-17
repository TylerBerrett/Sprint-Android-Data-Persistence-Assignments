package com.lambdaschool.sharedprefs.ui

import android.content.Context
import android.os.Environment
import com.lambdaschool.sharedprefs.JournalRepoInterface
import com.lambdaschool.sharedprefs.model.JournalEntry
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.util.ArrayList

class JournalFileRepo(var context: Context): JournalRepoInterface {

    override fun updateEntry(entry: JournalEntry) {

    }

    override fun deleteEntry(entry: JournalEntry) {

    }


    override fun createEntry(entry: JournalEntry) {
        val entryString = entry.toJsonObject()
        val filename = "journalEntry${entry.date}.json"
        writeToFile(filename, entryString.toString())
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
                val directory = context.filesDir
                return if (!directory.exists() && !directory.mkdirs()) {
                    context.cacheDir
                } else {
                    directory
                }
            } else {
                return context.cacheDir
            }
        }


    val isExternalStorageWriteable: Boolean
        get() {
            val state = Environment.getExternalStorageState()
            return state == Environment.MEDIA_MOUNTED
    }

    override fun readAllEntries(): MutableList<JournalEntry> {
        //get filelist
        val entries = ArrayList<JournalEntry>()

        //setup array list
        //read in files and convert to objects
        for (filename in filelist) {
            val json = readFromFile(filename)
            try {
                entries.add(JournalEntry(JSONObject(json)))
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return entries
    }

    val filelist: ArrayList<String>
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