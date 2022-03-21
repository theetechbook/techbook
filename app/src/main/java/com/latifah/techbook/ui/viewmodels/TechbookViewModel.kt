package com.latifah.techbook.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.latifah.techbook.database.models.Post
import com.latifah.techbook.database.models.User
import com.latifah.techbook.repositories.MainRepository
import com.latifah.techbook.ui.fragments.Login
import com.latifah.techbook.ui.fragments.Register
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TechbookViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

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

}