package com.latifah.techbook.database.firebase

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.latifah.techbook.database.models.Post
import com.latifah.techbook.database.models.User
import com.latifah.techbook.ui.fragments.Login
import com.latifah.techbook.ui.fragments.Register
import com.latifah.techbook.util.Constants

class Firestore {
    private val db = Firebase.firestore

    fun registerUser(user: User, registerFragment : Register) {
        // db.collection("Users")  SEPARATION OF CONCERNS: This is a magic string and it's better to put these strings in a file. That way if it needs to be changed we only need to change it in one spot

        db.collection(Constants.USERS) //Create a collection
            .document(getCurrentUserUID())
            .set(user) //enter user info
            .addOnSuccessListener {
                registerFragment.registerSuccess(user)
            }
    }

    fun loginUser(loginFragment : Login) {
        db.collection(Constants.USERS)
            .document(getCurrentUserUID())
            .get().addOnSuccessListener { document ->
                val loggedInUser = document.toObject<User>()
                if (loggedInUser != null) {
                    loginFragment.loginSuccess(loggedInUser)
                }
            }. addOnFailureListener { exception ->
                Log.e("LoginUser", "error logging in user ${exception.message}")
            }
    }

    fun addPost(post: Post) {
        db.collection(Constants.POST)
            .document()
    }
    private fun getCurrentUserUID() : String {
        return Firebase.auth.currentUser!!.uid
    }

}