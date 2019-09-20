package com.lambdaschool.sharedprefs

import com.lambdaschool.sharedprefs.model.JournalEntry

<<<<<<< HEAD
interface JournalRepoInterface{
=======
// TODO 1: Extract behavior from Prefs to an interface

interface JournalRepoInterface {
>>>>>>> 99aa2683da55bd186f4a7ef1f2648d71cd172c26
    fun createEntry(entry: JournalEntry)
    fun readAllEntries(): MutableList<JournalEntry>
    fun updateEntry(entry: JournalEntry)
    fun deleteEntry(entry: JournalEntry)
}