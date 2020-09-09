package com.kevincodes.architecturecomponent.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kevincodes.architecturecomponent.data.Note

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
                    ).fallbackToDestructiveMigration().build()
                DATABASE_INSTANCE = instance
            }
            return instance
        }

//        @JvmStatic
//        private val roomCallBack = object : RoomDatabase.Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                CoroutineScope(Main).launch {
//                    DATABASE_INSTANCE?.let {
//                        populateDb(it)
//                    }
//                }
//            }
//        }
//
//        private suspend fun populateDb(noteDatabase: NoteDatabase) {
//            val noteDao = noteDatabase.noteDao()
//            val populateJob = CoroutineScope(IO).launch {
//                noteDao.insert(Note(null, "First Item", " description", 1))
//                noteDao.insert(Note(null, "Second Item", " description", 1))
//                noteDao.insert(Note(null, "Third Item", " description", 1))
//                noteDao.insert(Note(null, "Fourth Item", " description", 1))
//                noteDao.insert(Note(null, "Fifth Item", " description", 1))
//                noteDao.insert(Note(null, "Sixth Item", " description", 1))
//            }
//            withContext(Main) {
//                populateJob.join()
//            }
//
//        }
    }


}