package com.latifah.techbook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.latifah.techbook.R
import com.latifah.techbook.databinding.FragmentEventsBinding


class Events : Fragment() {
    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!

    private val args: EventsArgs by navArgs()



    override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       // return inflater.inflate(R.layout.fragment_events,container,false)
        // Inflate the layout for this fragment
        _binding = FragmentEventsBinding.inflate(inflater, container,false)
        val view = binding.root


        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.eventTitle.text = args.eventName
        binding.eventVenue.text = args.eventVenue
        binding.eventDate.text = args.eventDate
        binding.eventTime.text = args.eventTime
        binding.eventDescription.text = args.eventDescription
        binding.eventOnline.text = args.onlineVenue
      //  binding.eventImage.setImageResource(id)
        //binding.eventLocation.text = args
    }


}