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
import com.latifah.techbook.database.models.User
import com.latifah.techbook.databinding.FragmentRegisterBinding
/**
 * A simple [Fragment] subclass.
 * Use the [Register.newInstance] factory method to
 * create an instance of this fragment.
 */

class Register : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase Auth
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {

        // initialize variable, inflate layout
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        when {
            TextUtils.isEmpty(binding.editTextFirstName.text.toString().trim { it <= ' ' }) -> {
                //should we come up with a better way to show an error?
                Toast.makeText(activity, "First name field cannot be blank.", Toast.LENGTH_SHORT).show()
            }
            TextUtils.isEmpty(binding.editTextLastName.text.toString().trim { it <= ' ' }) -> {
                //should we come up with a better way to show an error?
                Toast.makeText(activity, "Last name field cannot be blank.", Toast.LENGTH_SHORT).show()
            }
            TextUtils.isEmpty(binding.editTextRegisterEmailAddress.text.toString().trim { it <= ' ' }) -> {
                //should we come up with a better way to show an error?
                Toast.makeText(activity, "Email field cannot be blank.", Toast.LENGTH_SHORT).show()
            }
            TextUtils.isEmpty(binding.editTextRegisterPassword.text.toString().trim { it <= ' ' }) -> {
                //should we come up with a better way to show an error?
                Toast.makeText(activity, "Password field cannot be blank.", Toast.LENGTH_SHORT).show()
            }
            TextUtils.isEmpty(binding.editTextUsername.text.toString().trim { it <= ' ' }) -> {
                //should we come up with a better way to show an error?
                Toast.makeText(activity, "UserName name field cannot be blank.", Toast.LENGTH_SHORT).show()
            }
            else -> {
                val firstName : String = binding.editTextFirstName.text.toString().trim { it <= ' ' }
                val lastName : String = binding.editTextLastName.text.toString().trim { it <= ' ' }
                val email : String = binding.editTextRegisterEmailAddress.text.toString().trim { it <= ' ' }
                val password : String = binding.editTextRegisterPassword.text.toString().trim { it <= ' '}
                val userName : String = binding.editTextUsername.text.toString().trim { it <= ' ' }

                //Create an instance and register a user with an email and password
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val firebaseUser = auth.currentUser!!
                            val registeredEmail = firebaseUser.email!! //I'm using the email that comes from firebase because I know that it's already been authenticated
                            val user = User(firebaseUser.uid, firstName, lastName, registeredEmail, userName)
                            registerSuccess(user)
                        }
                        else {
                            Log.d("register user", "registerUser: ${task.exception!!.message}")
                            Toast.makeText(activity, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

            }
        }

    }

    fun registerSuccess(user: User) {
        val action = RegisterDirections.actionRegisterToProfile2(user.firstName, user.lastName, user.userName)
        findNavController().navigate(action)
    }

}