package com.latifah.techbook.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.latifah.techbook.R
import com.latifah.techbook.databinding.FragmentProfileBinding
/**
 * A simple [Fragment] subclass.
 * Use the [Profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class Profile : BaseFragment() {
    override var bottomNavigationViewVisibility = View.VISIBLE
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val args : ProfileArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        //binding.messageProfile.setOnClickListener {
          //  val action = ProfileDirections.actionProfile2ToContactFragment()
          //  findNavController().navigate(action)

            //val action = ProfileDirections.actionProfile2ToEventsList()
           // findNavController().navigate(action)
      //  }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.username.text = args.userName
        binding.name.text = "${args.firstName}  ${args.lastName}"

        // Here is the code slot for the button that saves edited profile data.
        binding.saveProfileButton.setOnClickListener {
            //private fun saveProfileText (?)
        }

        // This is Ana's "Contact Button Code"
//            binding.buttonContact.setOnClickListener {
//            val action = ProfileDirections.actionProfile2ToContactFragment()
//            findNavController().navigate(action)
        }
    }

