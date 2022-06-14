package com.greemoid.notesappmvvm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.greemoid.notesappmvvm.database.room.AppRoomDatabase
import com.greemoid.notesappmvvm.database.room.repository.RoomRepository
import com.greemoid.notesappmvvm.model.Note
import com.greemoid.notesappmvvm.utils.REPOSITORY
import com.greemoid.notesappmvvm.utils.TYPE_FIREBASE
import com.greemoid.notesappmvvm.utils.TYPE_ROOM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {
        Log.d("checkData", "MainViewModel initDatabase with type: $type")

        when(type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
    }

    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.create(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun readAllNotes() = REPOSITORY.readAll
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel Class")
    }

}