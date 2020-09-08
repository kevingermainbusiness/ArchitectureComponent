package com.kevincodes.architecturecomponent.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var title: String?,
    var description: String?,
    var priority: Int
)