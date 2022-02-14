package com.latifah.techbook.ui.fragments


import android.graphics.Insets.add
import android.media.metrics.Event
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.util.WorkSourceUtil.add
import com.latifah.techbook.R
import com.latifah.techbook.adapters.TechEventAdapter
import com.latifah.techbook.database.models.ContactItem
import com.latifah.techbook.database.models.DataSource
import com.latifah.techbook.database.models.EventsToday
import com.latifah.techbook.database.models.User
import com.latifah.techbook.databinding.EventsListFragmentBinding
import com.latifah.techbook.ui.viewmodels.EventsListViewModel
import org.intellij.lang.annotations.Language

class EventsList : Fragment() {
    private var _binding: EventsListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TechEventAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = EventsListFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        val myDataset = DataSource().loadEvents()
        val rvView = view.findViewById<RecyclerView>(R.id.rcyview)
        adapter = TechEventAdapter(myDataset)
        rvView.adapter = adapter
        // eventsView is the id of the RecyclerView in events_list_fragment xml
        rvView.layoutManager = LinearLayoutManager(requireContext())


        return view
    }
/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myDataset = DataSource().loadEvents()
        val rvView = view.findViewById<RecyclerView>(R.id.rcyview)
        adapter = TechEventAdapter(myDataset)
        rvView.adapter = adapter
        // eventsView is the id of the RecyclerView in events_list_fragment xml
        rvView.layoutManager = LinearLayoutManager(requireContext())

        //Log.i("ana","button clicked")
*/
        fun messageSuccess(eventsToday: EventsToday) {
            val action = EventsListDirections.actionEventsList2ToEvents2("Altanta")
            findNavController().navigate(action)
        }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun dummieEvent(size:Int):List<EventsToday> {
        val list = ArrayList<EventsToday>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_person_icon
                1 -> R.drawable.ic_contact_person
                else -> R.drawable.ic_navigate_next
            }
            val item = EventsToday( "Item $i", "Line 2")
            list += item
        }
        return list
    }

}









