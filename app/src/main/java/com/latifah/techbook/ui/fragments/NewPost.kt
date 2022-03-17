package com.latifah.techbook.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.latifah.techbook.R
import com.latifah.techbook.databinding.FragmentNewPostBinding


class NewPost : BaseFragment() {

    override var bottomNavigationViewVisibility = View.VISIBLE
    private var _binding: FragmentNewPostBinding? = null
    private val binding get() = _binding!!

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

}