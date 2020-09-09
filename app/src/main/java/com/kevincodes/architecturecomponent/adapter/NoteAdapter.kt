package com.kevincodes.architecturecomponent.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevincodes.architecturecomponent.R
import com.kevincodes.architecturecomponent.data.Note
import kotlinx.android.synthetic.main.note_item.view.*
import java.lang.StringBuilder

class NoteAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var notes: List<Note> = ArrayList()

    fun submitNotes(submittedNotes: List<Note>) {
        notes = submittedNotes
    }

    private class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) {
            itemView.text_view_title.text = note.title
            itemView.text_view_description.text = note.description
            itemView.text_view_priority.text = note.priority.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NoteHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is NoteHolder ->{
                holder.bind(notes[position])
            }
        }
    }

    override fun getItemCount(): Int = notes.size
}