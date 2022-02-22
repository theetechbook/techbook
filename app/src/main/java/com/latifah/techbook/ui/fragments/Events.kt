package com.latifah.techbook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.latifah.techbook.R


/**
 * A simple [Fragment] subclass.
 * Use the [Events.newInstance] factory method to
 * create an instance of this fragment.
 */
class Events : BaseFragment() {

    val args:EventsArgs by navArgs()

    override var bottomNavigationViewVisibility = View.VISIBLE


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val contactBut = view?.findViewById<Button>(R.id.contactBtn)
        val eventsBut = view?.findViewById<Button>(R.id.eventsBtn)

        contactBut?.setOnClickListener{
            val action = EventsDirections.actionEventsToContactFragment()
            findNavController().navigate(action)
        }
        return inflater.inflate(R.layout.fragment_events, container, false)

    }

   /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nameA: TextView = view.findViewById(R.id.eventTxtV)
        val locationA:TextView =view.findViewById(R.id.eventsTxt)
        val name = args.name
        val location = args.location
        nameA.text = name.toString()
        locationA.text = location.toString()
    }
    */

}