package com.greemoid.notesappmvvm.database.firebase

import android.util.Log
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.greemoid.notesappmvvm.database.DatabaseRepository
import com.greemoid.notesappmvvm.model.Note
import com.greemoid.notesappmvvm.utils.Constants
import com.greemoid.notesappmvvm.utils.EMAIL
import com.greemoid.notesappmvvm.utils.FIREBASE_ID
import com.greemoid.notesappmvvm.utils.PASSWORD

class FirebaseRepository: DatabaseRepository {
    private val auth = FirebaseAuth.getInstance()

    private val  database = Firebase.database.reference
        .child(auth.currentUser?.uid.toString())

    override val readAll: LiveData<List<Note>>
        get() = AllNotesLiveData()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        val noteId = database.push().key.toString()
        val mapNotes = hashMapOf<String, Any>()

        mapNotes[FIREBASE_ID] = noteId
        mapNotes[Constants.KEYS.TITLE] = note.title
        mapNotes[Constants.KEYS.SUBTITLE] = note.subtitle

        database.child(noteId)
            .updateChildren(mapNotes)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failed to add new note") }
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun signOut() {
        auth.signOut()
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        auth.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener{ onSuccess() }
            .addOnFailureListener {
                auth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFail(it.message.toString()) }
            }
    }
}