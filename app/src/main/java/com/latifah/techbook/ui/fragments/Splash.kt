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
 * Use the [Splash.newInstance] factory method to
 * create an instance of this fragment.
 */

class Splash : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        view.findViewById<Button>(R.id.login_button_splash).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_splash_to_login)
        }

        view.findViewById<Button>(R.id.register_button_splash).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_splash_to_register)
        }


        return view
    }

}