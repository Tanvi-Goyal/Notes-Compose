package com.example.myapplication

import android.app.Application
import com.example.myapplication.data.NotesRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class NotesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val applicationScope = CoroutineScope(SupervisorJob())

        val database by lazy { NotesDatabase.getDatabase(this, applicationScope) }
        val repository by lazy { NotesRepository(database.noteDao()) }
    }
}