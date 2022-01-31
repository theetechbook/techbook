package com.latifah.techbook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.latifah.techbook.R

/**
 * A simple [Fragment] subclass.
 * Use the [Register.newInstance] factory method to
 * create an instance of this fragment.
 */

class Register : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {

        // initialize variable, inflate layout
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        //buttons, actionables
        view.findViewById<Button>(R.id.register_button).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_register_to_login)
        }

        // return
        return view
    }

}