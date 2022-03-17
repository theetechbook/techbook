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

    companion object {
        val IMAGE_REQUEST_CODE = 1_000;
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // initialize variable, inflate layout
        _binding = FragmentNewPostBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addMedia.setOnClickListener {
            selectMedia()
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



}