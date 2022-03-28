package com.latifah.techbook.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.EventListener
import com.latifah.techbook.database.LikedPost
import com.latifah.techbook.database.models.Post
import com.latifah.techbook.database.models.User
import com.latifah.techbook.repositories.MainRepository
import com.latifah.techbook.ui.fragments.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TechbookViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

//   Link for returning Livedata from Firebase:
//   https://medium.com/@deepak140596/firebase-firestore-using-view-models-and-livedata-f9a012233917

    var _firstName: String? = ""
    private val _userFirstName: MutableLiveData<String> = MutableLiveData(_firstName)
    var userFirstName: LiveData<String> = _userFirstName
    val posts: MutableLiveData<List<Post?>?> = MutableLiveData()
    val userPosts: MutableLiveData<List<Post?>?> = MutableLiveData()
    val userInfo: MutableLiveData<User?> = MutableLiveData()

    private var _likedPosts = MutableLiveData<ArrayList<LikedPost>>()
    var likedPosts : LiveData<ArrayList<LikedPost>> = _likedPosts



    fun registerUser(user: User, registerFragment : Register) {

        mainRepository.registerUser(user, registerFragment)

    }

    fun loginUser(loginFragment : Login) {

        mainRepository.loginUser(loginFragment)

    }

    fun addPost(post: Post) {

        mainRepository.addPost(post)

    }

    //posts: MutableList<Map<String, Any>>
    fun getAllPosts(): LiveData<List<Post?>?> {

        mainRepository.getAllPosts().addSnapshotListener(EventListener { value, e ->
            if (e != null) {
                Log.w("Listen failed.", e)
                posts.value = null
                return@EventListener
            }
            var postsList : MutableList<Post>? = mutableListOf()
            for (doc in value!!) {
                var addressItem = doc.toObject(Post::class.java)
                postsList?.add(addressItem)
            }
            //posts.value = postsList
            posts.setValue(postsList)
        })

        return posts

    }

    fun getPostByUserId(): LiveData<List<Post?>?> {

            mainRepository.getPostByUserId().addSnapshotListener(EventListener { value, e ->
                if (e != null) {
                    Log.w("Listen failed.", e)
                    userPosts.value = null
                    return@EventListener
                }
                var postsList : MutableList<Post>? = mutableListOf()
                for (doc in value!!) {
                    var addressItem = doc.toObject(Post::class.java)
                    postsList?.add(addressItem)
                }
                //posts.value = postsList
                userPosts.setValue(postsList)
            })

            return userPosts

        }


    fun getCurrentUserUID() : String {

       return mainRepository.getCurrentUserUID()

    }

//    fun getCurrentUserFirstName(viewModel: TechbookViewModel, fragment: Profile) {
//        viewModelScope.launch {
//            mainRepository.getUserInfo(viewModel, fragment)
//            Log.d("mainRepo returned firstName", "$_firstName")
//        }
//
//
//    }

//    fun setCurrentUserInfo(firstName: String?,lastName: String?, userName: String?, fragment: Profile) {
//            Log.d("firstName from mainRepository", "$firstName")
//            _firstName = firstName
//            fragment.setCurrentUserFirstName(firstName, lastName, userName)
//            Log.d("_firstName in viewModel is now", "$_firstName")
//
//        //Log.i("user firstName is",  "${mainRepository.getCurrentUserFirstName()}")
//
//
//    }

    fun getCurrentUserLastName(): String? {

        // Log.d("getUserLastName", "Calling function from Repository")
        return mainRepository.getCurrentUserLastName()

    }

    fun setPosts(posts: MutableList<String?>, fragment: Homepage) {
        //fragment.setupRecyclerView(posts, fragment)
    }
    fun getCurrentUserName() : String? {

        return mainRepository.getCurrentUserName()

    }

//    fun getLikedPosts(){
//        viewModelScope.launch{
//            Log.d("getting Liked Posts from main Repo", "${mainRepository.getLikedPosts().value}")
//            _likedPosts = mainRepository.getLikedPosts()
//        }
 //   }

        fun savePost(post: Post){
            viewModelScope.launch{
                mainRepository.savePost(post)
        }
      }

    fun getUserInfo(): LiveData<User?> {
        mainRepository.getUserInfo().addSnapshotListener(EventListener { value, e ->
            if (e != null) {
                Log.w("Listen failed.", e)
                userInfo.value = null
                return@EventListener
            }
           var user = value!!.toObject(User::class.java)
            userInfo.value = user
        })
        return userInfo
    }

    fun updateUserInfo(firstName: String, lastName: String, userName: String, website : String, bio : String, email : String) {
        mainRepository.updateUserInfo(firstName, lastName, userName, website, bio , email)
    }
}