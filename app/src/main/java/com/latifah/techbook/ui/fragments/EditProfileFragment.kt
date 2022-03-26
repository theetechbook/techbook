package com.latifah.techbook.ui.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.latifah.techbook.R
import com.latifah.techbook.databinding.FragmentEditProfileBinding


class EditProfileFragment : Fragment() {
    private var _binding:  FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var imageUri : Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvChangeProfilePhoto.setOnClickListener {
            selectMedia()
        }
    }

    private fun selectMedia() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"

        startActivityForResult(intent, NewPost.IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NewPost.IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Log.d("image uploaded", "${data?.data}")
            activity?.let {
                Glide.with(it)
                    .load(data?.data)
                    .into(binding.avatar)
            }
            imageUri = data?.data!!
        }
    }
}