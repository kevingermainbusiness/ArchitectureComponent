package com.kevincodes.architecturecomponent.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kevincodes.architecturecomponent.R
import com.kevincodes.architecturecomponent.mvvm.NoteViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val noteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(this.application)
        ).get(NoteViewModel::class.java)
        noteViewModel.getAllTheNotes().observe(this, Observer {
            Toast.makeText(applicationContext, "onChanged", Toast.LENGTH_LONG).show()
        })
    }
}