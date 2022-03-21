package com.latifah.techbook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.latifah.techbook.R
import com.latifah.techbook.databinding.FragmentNewPostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewPost : BaseFragment() {

    override var bottomNavigationViewVisibility = View.VISIBLE
    private var _binding: FragmentNewPostBinding? = null
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // initialize variable, inflate layout
        _binding = FragmentNewPostBinding.inflate(inflater, container, false)

        val view = binding?.root

        //buttons, actionables
        // view.findViewById<Button>(R.id.confirm_new_media).setOnClickListener {
        //    Navigation.findNavController(view).navigate(R.id.action_newPost2_to_dashboard2)


        // return
        return view
    }
}