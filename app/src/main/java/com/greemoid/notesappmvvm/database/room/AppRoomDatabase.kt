package com.greemoid.notesappmvvm.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.greemoid.notesappmvvm.database.room.dao.NoteRoomDao
import com.greemoid.notesappmvvm.model.Note


@Database(entities = [Note::class], version = 2)
abstract class AppRoomDatabase: RoomDatabase() {

    abstract fun getRoomDao(): NoteRoomDao

    companion object {

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase {
            return if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    "notes.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE as AppRoomDatabase
            } else INSTANCE as AppRoomDatabase
        }
    }
}