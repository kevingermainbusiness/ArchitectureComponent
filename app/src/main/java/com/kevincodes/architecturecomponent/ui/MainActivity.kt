package com.kevincodes.architecturecomponent.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kevincodes.architecturecomponent.R
import com.kevincodes.architecturecomponent.adapter.NoteAdapter
import com.kevincodes.architecturecomponent.data.Note
import com.kevincodes.architecturecomponent.mvvm.NoteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val noteAdapter = NoteAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = noteAdapter
        }
        // Bind the ViewModel to its factory & owner
        noteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(this.application)
        ).get(NoteViewModel::class.java)

        noteViewModel.insert(Note(null,"My First Item","Description",1))
        noteViewModel.insert(Note(null,"My Second Item","Description",2))
        noteViewModel.insert(Note(null,"Third item","Description",3))
        noteViewModel.insert(Note(null,"Fourth item","Description",4))

        // Observe the data
        noteViewModel.getAllTheNotes().observe(this, {
            noteAdapter.submitNotes(it)
            Log.d("RoomInsert", it[0].id.toString())
            Log.d("RoomInsert", it[1].id.toString())
            Log.d("RoomInsert", it[2].id.toString())
            Log.d("RoomInsert", it[3].id.toString())
        })
        noteAdapter.notifyDataSetChanged()
    }
}