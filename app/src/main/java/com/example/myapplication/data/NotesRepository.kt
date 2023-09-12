package com.example.myapplication.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class NotesRepository(private val notesDao: NoteDao) {

    val allNotes: Flow<List<Note>> = notesDao.getNotes()

    @WorkerThread
    suspend fun insert(word: Note) {
        notesDao.insert(word)
    }
}