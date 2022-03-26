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

/*
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
        db.collection("post")
            .add(post)
            .addOnSuccessListener { documentReference ->
                Log.d("add post", documentReference.id)
                //navigate to home page
            }
            .addOnFailureListener { e ->
                e.message?.let { Log.w("Add Post Err: ", it) }
            }
    }

    fun getAllPosts() {
        val posts = mutableListOf<Map<String, Any>>()
        db.collection("post")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("GET ALL POSTS", "data: ${document.data}")
                    posts.add(document.data)
                }
                Log.d("GET ALL POSTS List", "data: ${posts}")

            }
            .addOnFailureListener { exception ->
                Log.d("GET ALL POSTS", "Error getting documents: ", exception)
            }
    }

    fun getPostByUserId() {
        db.collection("post")
            .whereEqualTo("userUid", getCurrentUserUID())
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("GET ALL POSTS By UID", "data: ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("GET ALL POSTS By UID", "Error getting documents: ", exception)
            }
    }

    fun getCurrentUserUID() : String {
        return Firebase.auth.currentUser!!.uid
    }
*/
}