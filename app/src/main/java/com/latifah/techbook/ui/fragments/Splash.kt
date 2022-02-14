package com.latifah.techbook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.latifah.techbook.R
import com.latifah.techbook.databinding.FragmentRegisterBinding
import com.latifah.techbook.databinding.FragmentSplashBinding

/**
 * A simple [Fragment] subclass.
 * Use the [Splash.newInstance] factory method to
 * create an instance of this fragment.
 */

class Splash : BaseFragment() {

    override var bottomNavigationViewVisibility = View.GONE

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root


//        val view = inflater.inflate(R.layout.fragment_splash, container, false)
//
//        view.findViewById<Button>(R.id.login_button_splash).setOnClickListener {
//            Navigation.findNavController(view).navigate(R.id.action_splash_to_login)
//        }
//
//        view.findViewById<Button>(R.id.register_button_splash).setOnClickListener {
//            Navigation.findNavController(view).navigate(R.id.action_splash_to_register)
//        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButtonSplash.setOnClickListener {
            val action = SplashDirections.actionSplashToRegister()
            findNavController().navigate(action)
        }

        binding.loginButtonSplash.setOnClickListener {
            val action = SplashDirections.actionSplashToLogin()
            findNavController().navigate(action)
        }

    }

}