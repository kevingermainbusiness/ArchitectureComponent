package com.kevincodes.architecturecomponent.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kevincodes.architecturecomponent.data.Note
import kotlinx.coroutines.launch

class NoteViewModel constructor(application: Application) : AndroidViewModel(application) {

    var noteRepository: NoteRepository = NoteRepository(application)
    private var allNotes: LiveData<List<Note>> =noteRepository.getAllNotes()

//    init {
//        viewModelScope.launch {
//            allNotes = noteRepository.getAllNotes()
//        }
//    }

    fun insert(note: Note) {
        viewModelScope.launch { noteRepository.insert(note) }
    }

    fun update(note: Note) {
        viewModelScope.launch { noteRepository.update(note) }
    }

    fun delete(note: Note) {
        viewModelScope.launch { noteRepository.delete(note) }
    }

    fun deleteAllNotes() {
        viewModelScope.launch { noteRepository.deleteAllNotes() }
    }

    fun getAllTheNotes(): LiveData<List<Note>> {
        return allNotes
    }

}