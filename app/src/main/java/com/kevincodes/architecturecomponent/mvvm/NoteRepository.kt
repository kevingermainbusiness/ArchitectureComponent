package com.kevincodes.architecturecomponent.mvvm

import android.app.Application
import android.os.Handler
import androidx.lifecycle.LiveData
import com.kevincodes.architecturecomponent.data.Note
import com.kevincodes.architecturecomponent.room.NoteDao
import com.kevincodes.architecturecomponent.room.NoteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteRepository(application: Application) {
    private var noteDao: NoteDao
    private var allNotes: LiveData<List<Note>>

    init {
        val noteDatabase = NoteDatabase.getInstance(application)
        noteDao = noteDatabase.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    suspend fun insert(note: Note) {
        val insertJob = CoroutineScope(IO).launch {
            withContext(Main) {
                noteDao.insert(note)
            }
        }
        withContext(Main) { insertJob.join() }
    }

    suspend fun update(note: Note) {
        val updateJob = CoroutineScope(IO).launch {
            withContext(Main) {
                noteDao.update(note)
            }
        }
        withContext(Main) { updateJob.join() }
    }

    suspend fun delete(note: Note) {
        val deleteJob = CoroutineScope(IO).launch {
            withContext(Main) {
                noteDao.delete(note)
            }
        }
        withContext(Main) { deleteJob.join() }
    }

    suspend fun deleteAllNotes() {
        val deleteAllNotesJob = CoroutineScope(IO).launch {
            withContext(Main) {
                noteDao.deleteAllNotes()
            }
        }
        withContext(Main) { deleteAllNotesJob.join() }
    }

    fun getAllNotes(): LiveData<List<Note>> {
        Handler().post {
            allNotes
        }
        return allNotes
    }


}