package com.latifah.techbook.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latifah.techbook.database.LikedPost
import com.latifah.techbook.database.models.Post
import com.latifah.techbook.database.models.User
import com.latifah.techbook.repositories.MainRepository
import com.latifah.techbook.ui.fragments.BaseFragment
import com.latifah.techbook.ui.fragments.Login
import com.latifah.techbook.ui.fragments.Profile
import com.latifah.techbook.ui.fragments.Register
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TechbookViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    var _firstName: String? = ""
    private val _userFirstName: MutableLiveData<String> = MutableLiveData()
    var userFirstName: LiveData<String> = _userFirstName
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

    fun getAllPosts() {

        mainRepository.getAllPosts()

    }

    fun getPostByUserId() {

        mainRepository.getPostByUserId()

    }

    fun getCurrentUserUID() : String {

       return mainRepository.getCurrentUserUID()

    }

    fun getCurrentUserFirstName(viewModel: TechbookViewModel, fragment: Profile) {
        viewModelScope.launch {
            mainRepository.setCurrentUserFirstName(viewModel, fragment)
        }


    }

    fun setCurrentUserInfo(firstName: String?,lastName: String?, userName: String?, fragment: Profile) {
            Log.d("firstName from mainRepository", "$firstName")
            _firstName = firstName
            fragment.setCurrentUserFirstName(firstName, lastName, userName)
            Log.d("_firstName is now", "$_firstName")

        //Log.i("user firstName is",  "${mainRepository.getCurrentUserFirstName()}")


    }

    fun getCurrentUserLastName(): String? {

        // Log.d("getUserLastName", "Calling function from Repository")
        return mainRepository.getCurrentUserLastName()

    }

    fun getCurrentUserName() : String? {

        return mainRepository.getCurrentUserName()

    }

    fun getLikedPosts(){
        viewModelScope.launch{
            Log.d("getting Liked Posts from main Repo", "${mainRepository.getLikedPosts().value}")
            _likedPosts = mainRepository.getLikedPosts()
        }

    }
}