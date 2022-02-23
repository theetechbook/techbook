package com.latifah.techbook.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.latifah.techbook.R
import com.latifah.techbook.database.firebase.Firestore
import com.latifah.techbook.database.models.User
import com.latifah.techbook.databinding.FragmentLoginBinding

class Login : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener {
            loginUser()
        }
    }

    fun loginUser () {
        when {
            TextUtils.isEmpty(binding.editTextEmailAddress.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(activity, "Please enter an email.", Toast.LENGTH_SHORT).show()
            }
            TextUtils.isEmpty(binding.editTextPassword.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(activity, "Please enter password", Toast.LENGTH_SHORT).show()

            }
            else -> {
                val email : String = binding.editTextEmailAddress.text.toString().trim{ it <= ' '}
                val password : String = binding.editTextPassword.text.toString().trim { it <= ' ' }
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->

                        if(task.isSuccessful){
                            Firestore().loginUser(this)
                        }
                    }
            }
        }
    }

    fun loginSuccess(user : User) {
        val action = LoginDirections.actionLoginToProfile2(user.firstName, user.lastName, user.userName)
        findNavController().navigate(action)
    }
}