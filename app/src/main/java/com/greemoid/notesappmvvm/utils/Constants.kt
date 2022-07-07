package com.greemoid.notesappmvvm.utils

import com.greemoid.notesappmvvm.database.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"
const val FIREBASE_ID = "firebaseId"

lateinit var REPOSITORY: DatabaseRepository
lateinit var EMAIL: String
lateinit var PASSWORD: String


object Constants {
    object KEYS {
        const val NOTES_TABLE = "notes_table"
        const val ID = "Id"
        const val NONE = "none"
        const val TITLE = "title"
        const val SUBTITLE = "subtitle"
    }

    object SCREENS {
        const val START_SCREEN = "start_screen"
        const val ADD_SCREEN = "add_screen"
        const val NOTE_SCREEN = "note_screen"
        const val MAIN_SCREEN = "main_screen"
    }

}

