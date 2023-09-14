package com.example.myapplication

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.NotesDatabase.Companion.INSTANCE
import com.example.myapplication.data.Note
import com.example.myapplication.data.NoteDao
import com.example.myapplication.data.NoteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NotesDatabaseCallback(
    private val scope: CoroutineScope
): RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        INSTANCE?.let { database ->
            scope.launch {
                populateDatabase(database.noteDao())
            }
        }
    }

    private suspend fun populateDatabase(noteDao: NoteDao) {
        // Delete all content here.
        noteDao.deleteAll()

        // Add sample words.
        var note = NoteEntity(1, "Note 1", "Content", 32)
        noteDao.insert(note)

        // TODO: Add your own words!
    }
}