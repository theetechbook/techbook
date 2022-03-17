package com.latifah.techbook.ui.fragments

import android.app.Activity
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.latifah.techbook.R
import com.latifah.techbook.database.firebase.Firestore
import com.latifah.techbook.database.models.Post
import com.latifah.techbook.databinding.FragmentNewPostBinding
import java.util.*


class NewPost : BaseFragment() {

    override var bottomNavigationViewVisibility = View.VISIBLE
    private var _binding: FragmentNewPostBinding? = null
    private val binding get() = _binding!!
    private lateinit var imageUri : Uri
    private lateinit var storage: FirebaseStorage

    companion object {
        val IMAGE_REQUEST_CODE = 1_000;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storage = Firebase.storage
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // initialize variable, inflate layout
        _binding = FragmentNewPostBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addMedia.setOnClickListener {
            selectMedia()
        }

        binding.confirmNewMedia.setOnClickListener {
            uploadGif()
        }
    }

    //TODO: UPDATE THIS FUNCTION TO USE UPDATED startActivityForResult FUNCTION
    private fun selectMedia() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"

        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Log.d("image uploaded", "${data?.data}")
            activity?.let {
                Glide.with(it)
                    .load(data?.data)
                    .into(binding.imageViewUploadGif)
            }
            imageUri = data?.data!!
        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun uploadGif() {
        val storageRef = storage.reference

        val formatter = SimpleDateFormat("MM_dd_yyyy_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)

        val gifUploadRef = storageRef.child("/gifs/$fileName")
        var uploadTask = gifUploadRef.putFile(imageUri)

        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            gifUploadRef.downloadUrl
        } .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                //I want to add the post to firestore here as well
                val post = Post(Firestore().getCurrentUserUID(), binding.addCaption.text.toString(), "$downloadUri", "namiswan", "$downloadUri" )
                Firestore().addPost(post)
                Log.d("DOWNLOAD URI", "$downloadUri")
            } else {
                //handle error
                Toast.makeText(activity, "Error adding your post", Toast.LENGTH_SHORT).show()
            }
        }
}
}