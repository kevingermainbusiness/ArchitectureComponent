package com.kevincodes.architecturecomponent.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kevincodes.architecturecomponent.data.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        // When database is null
        @Volatile
        var DATABASE_INSTANCE: NoteDatabase? = null

        fun getInstance(mContext: Context): NoteDatabase {
            var instance = DATABASE_INSTANCE
            if (instance == null) {
                instance =
                    Room.databaseBuilder(
                        mContext.applicationContext,
                        NoteDatabase::class.java,
                        "notes-database"
                    ).fallbackToDestructiveMigration().addCallback(roomCallBack).build()
                DATABASE_INSTANCE = instance
            }
            return instance
        }

        @JvmStatic
        private val roomCallBack = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Main).launch {
                    populateDb(DATABASE_INSTANCE!!)
                }
            }
        }

        private suspend fun populateDb(noteDatabase: NoteDatabase) {
            val noteDao = noteDatabase.noteDao()
            val populateJob = CoroutineScope(IO).launch {
                withContext(Main) {
                    noteDao.insert(Note(null, "First Item", " description", 1))
                    noteDao.insert(Note(null, "Second Item", " description", 1))
                    noteDao.insert(Note(null, "Third Item", " description", 1))
                    noteDao.insert(Note(null, "Fourth Item", " description", 1))
                    noteDao.insert(Note(null, "Fifth Item", " description", 1))
                    noteDao.insert(Note(null, "Sixth Item", " description", 1))
                }
            }
            withContext(Main) {
                populateJob.join()
            }

        }
    }


}