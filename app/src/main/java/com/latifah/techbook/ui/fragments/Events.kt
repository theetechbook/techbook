package com.latifah.techbook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.latifah.techbook.R
import com.latifah.techbook.databinding.FragmentEventsBinding


/**
 * A simple [Fragment] subclass.
 * Use the [Events.newInstance] factory method to
 * create an instance of this fragment.
 */
class Events : Fragment() {
    private var _binding: FragmentEventsBinding?=null
    private val binding get() = _binding!!

    private val args : EventsListArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false)
    }


}