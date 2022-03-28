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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.latifah.techbook.R
import com.latifah.techbook.databinding.FragmentEditProfileBinding
import com.latifah.techbook.ui.viewmodels.TechbookViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditProfileFragment : Fragment() {
    private var _binding:  FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var imageUri : Uri
    private val viewModel: TechbookViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        viewModel.getUserInfo()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userInfo.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.etFullName.setText("${it.firstName} ${it.lastName}")
                binding.etUsername.setText(it.userName)
                binding.etWebsite.setText(it.website)
                binding.etBio.setText(it.bio)
                binding.etEmail.setText(it.email)

            }
        }
        binding.tvChangeProfilePhoto.setOnClickListener {
            selectMedia()
        }

        binding.saveButton.setOnClickListener {
            Log.d("save button pressed", "need function to update user Info in Firebase")
            viewModel.updateUserInfo(binding.etFullName.text.toString(), binding.etFullName.text.toString(), binding.etUsername.text.toString(), binding.etWebsite.text.toString(), binding.etBio.text.toString(), binding.etEmail.text.toString())
            val action = EditProfileFragmentDirections.actionEditProfileFragmentToProfile2()
            findNavController().navigate(action)
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