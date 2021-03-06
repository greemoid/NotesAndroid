package com.greemoid.notesappmvvm.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.greemoid.notesappmvvm.model.Note


@Dao
interface NoteRoomDao {

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): LiveData<List<Note>>

    @Delete
    suspend fun deleteNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)
}