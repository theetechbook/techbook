package com.latifah.techbook.database.firebase

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.latifah.techbook.database.models.User
import com.latifah.techbook.ui.fragments.Register
import com.latifah.techbook.util.Constants

class Firestore {
    private val db = Firebase.firestore

    fun registerUser(user: User, registerFragment : Register) {
        // db.collection("Users")  SEPARATION OF CONCERNS: This is a magic string and it's better to put these strings in a file. That way if it needs to be changed we only need to change it in one spot

        db.collection(Constants.USERS) //Create a collection
            .document(getCurrentUserUID())
            .set(user)
            .addOnSuccessListener {
                registerFragment.registerSuccess(user)
            }
    }

    private fun getCurrentUserUID() : String {
        return Firebase.auth.currentUser!!.uid
    }
}