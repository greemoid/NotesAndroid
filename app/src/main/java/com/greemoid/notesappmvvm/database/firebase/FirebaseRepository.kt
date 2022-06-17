package com.greemoid.notesappmvvm.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.greemoid.notesappmvvm.database.DatabaseRepository
import com.greemoid.notesappmvvm.model.Note
import com.greemoid.notesappmvvm.utils.EMAIL
import com.greemoid.notesappmvvm.utils.PASSWORD

class FirebaseRepository: DatabaseRepository {

    val auth = FirebaseAuth.getInstance()

    override val readAll: LiveData<List<Note>>
        get() = TODO("Not yet implemented")

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
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