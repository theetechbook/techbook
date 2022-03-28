package com.latifah.techbook.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.latifah.techbook.database.LikedPost
import com.latifah.techbook.database.TechbookDao
import com.latifah.techbook.database.firebase.Firestore
import com.latifah.techbook.database.models.Post
import com.latifah.techbook.database.models.User
import com.latifah.techbook.network.TechEventApiService
import com.latifah.techbook.ui.fragments.*
import com.latifah.techbook.ui.viewmodels.TechbookViewModel
import com.latifah.techbook.util.Constants
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val techbookDao: TechbookDao,
    private val techEventApiService: TechEventApiService,
    private val db: FirebaseFirestore
) {

    private val _userID = MutableLiveData<String>()
    val userID : LiveData<String> = _userID
    var firstName: String? = ""

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

    suspend fun savePost(post: Post) {
        val likedPost = LikedPost(post.userUid, post.text, post.gif_url, post.username)
        techbookDao.insertLikedPost(likedPost)
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

    //For the home page
//    fun getAllPosts(viewModel: TechbookViewModel, fragment: Homepage) {
//        val posts = mutableListOf<String?>()
//        db.collection("post")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    Log.d("GET ALL POSTS", "data: ${document.data}")
//                    for (post in document.data.values) {
//                        posts.add(post as String?)
//                    }
//                    viewModel.setPosts(posts, fragment)
//                }
//                Log.d("GET ALL POSTS List", "data: ${posts}")
//            }
//            .addOnFailureListener { exception ->
//                Log.d("GET ALL POSTS", "Error getting documents: ", exception)
//            }
//    }

    fun getAllPosts(): CollectionReference {
        val posts = mutableListOf<String?>()
        val collectionReference = db.collection("post")
        return collectionReference
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    Log.d("GET ALL POSTS", "data: ${document.data}")
//                    for (post in document.data.values) {
//                        posts.add(post as String?)
//                    }
//                    viewModel.setPosts(posts, fragment)
//                }
//                Log.d("GET ALL POSTS List", "data: ${posts}")
//            }
//            .addOnFailureListener { exception ->
//                Log.d("GET ALL POSTS", "Error getting documents: ", exception)
//            }
    }

    //For the posts on Profile Page
//    fun getPostByUserId() {
//        db.collection("post")
//            .whereEqualTo("userUid", getCurrentUserUID())
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    Log.d("GET ALL POSTS By UID", "data: ${document.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d("GET ALL POSTS By UID", "Error getting documents: ", exception)
//            }
//    }

    fun getPostByUserId(): Query {
        val collectionReference = db.collection("post")
            .whereEqualTo("userUid", getCurrentUserUID())
            //.get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    Log.d("GET ALL POSTS By UID", "data: ${document.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d("GET ALL POSTS By UID", "Error getting documents: ", exception)
//            }
        return collectionReference
    }

    fun getCurrentUserUID() : String {
        return Firebase.auth.currentUser!!.uid
    }

    fun setCurrentUserFirstName(viewModel: TechbookViewModel, fragment: Profile) {
        //return Firebase.auth.currentUser!!.email
        var user: User? = null
        val docRef = db.collection("users").document(getCurrentUserUID())
        Log.d("MainRepo", "Starting on Success Listener")
        docRef.get().addOnSuccessListener { documentSnapshot ->
            user = documentSnapshot.toObject<User>()
            viewModel.setCurrentUserInfo(user?.firstName, user?.lastName, user?.userName, fragment)
            firstName = user?.firstName
            Log.d("setting user firstName", "$user, and first name is ${user?.firstName}")
            Log.d("firstName is set in onSuccess", "$firstName")
            return@addOnSuccessListener
        }
        Log.d("firstName is set in mainRepo", "$firstName")

    }

    fun getCurrentUserFirstName() : String? {
        //return Firebase.auth.currentUser!!.email
        //var user: User? = null
//        val docRef = db.collection("users").document(getCurrentUserUID())
//        docRef.get().addOnSuccessListener { documentSnapshot ->
//            val user = documentSnapshot.toObject<User>()
//            firstName = user?.firstName
//            Log.d("userInfo", "$user, and first name is ${user?.firstName}")
//        }
//        Log.d("firstName", "${firstName}")
        Log.d("getting FirstName", "$firstName")
        return firstName
    }

     fun getCurrentUserLastName(): String? {
         var user: User? = null
         var lastName: String? = ""
         val docRef = db.collection("users").document(getCurrentUserUID())
         docRef.get().addOnSuccessListener { documentSnapshot ->
             user = documentSnapshot.toObject<User>()
             Log.d("userInfo", "$user, and first name is ${user?.firstName}")
         }
         Log.d("lastName", "${lastName}")
         return lastName

//         var keysArrayList = arrayListOf<String>()
//         Log.d("getUserLastName", "Calling function")
//         db.collection("users")
//             .whereEqualTo("userUid", getCurrentUserUID())
//             .get()
//             .addOnSuccessListener { result ->
//                 Log.d("success", "${result.documents}")
////                 for (document in result) {
////                     Log.d("GET ALL POSTS By UID", "data: ${document.data.keys}")
////                     for (key in document.data.keys) {
////                     keysArrayList.add(key) }
////                 }
//             }
//             .addOnFailureListener { exception ->
//                 Log.d("GET ALL POSTS By UID", "Error getting documents: ", exception)
//             }
//         return keysArrayList
     }

    fun getCurrentUserName() : String? {
        var user: User? = null
        val docRef = db.collection("users").document(getCurrentUserUID())
        docRef.get().addOnSuccessListener { documentSnapshot ->
            user = documentSnapshot.toObject<User>()
            Log.d("userInfo", "$user, and first name is ${user?.firstName}")
        }
        Log.d("userName", "${user?.userName}")

        return user?.userName
    }

//    suspend fun getLikedPosts(): MutableLiveData<ArrayList<LikedPost>> {
//        return techbookDao.getLikedPosts()
//    }
}