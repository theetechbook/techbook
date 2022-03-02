package com.latifah.techbook.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.latifah.techbook.R
import com.latifah.techbook.database.models.EventsToday
import com.latifah.techbook.databinding.EventsListFragmentBinding
import com.latifah.techbook.databinding.FragmentEventsBinding


/**
 * A simple [Fragment] subclass.
 * Use the [Events.newInstance] factory method to
 * create an instance of this fragment.
 */
class Events : BaseFragment() {
    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding
   // private var dummieData = dummieEvent(0)

    val args:EventsArgs by navArgs()

    override var bottomNavigationViewVisibility = View.VISIBLE


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentEventsBinding.inflate(inflater,container,false)
      val view = binding?.root
       // return inflater.inflate(R.layout.fragment_events, container, false)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        val toolbar = view?.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.setupWithNavController(navController, appBarConfiguration)
        Log.i("test2", "test3")
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.eventTitle?.text = args.eventName
        binding?.eventVenue?.text = args.eventVenue
        binding?.eventDate?.text = args.eventDate.toString()
        binding?.eventTime?.text = args.eventTime
        binding?.eventDescription?.text = args.eventDescription

        //  binding.eventImage.setImageResource(id)
        //binding.eventLocation.text = args



    }

    override fun onDestroy() {
        super.onDestroy()
        // _binding = null
    }



}