package com.latifah.techbook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.latifah.techbook.R


class NewPost : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // initialize variable, inflate layout
        val view = inflater.inflate(R.layout.fragment_new_post, container, false)

        //buttons, actionables
        view.findViewById<Button>(R.id.confirm_new_media).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_newPost2_to_dashboard2)
        }

        // return
        return view
    }
}