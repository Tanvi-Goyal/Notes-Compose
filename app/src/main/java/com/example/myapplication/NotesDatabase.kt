package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.NoteDao
import com.example.myapplication.data.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
public abstract class NotesDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes_db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}