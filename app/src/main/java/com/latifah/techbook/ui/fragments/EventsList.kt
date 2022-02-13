package com.latifah.techbook.ui.fragments


import android.graphics.Insets.add
import android.media.metrics.Event
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.util.WorkSourceUtil.add
import com.latifah.techbook.adapters.TechEventAdapter
import com.latifah.techbook.database.models.EventsToday
import com.latifah.techbook.databinding.EventsListFragmentBinding
import com.latifah.techbook.ui.viewmodels.EventsListViewModel
import org.intellij.lang.annotations.Language

class EventsList : Fragment() {
    private var _binding: EventsListFragmentBinding?=null
    private val binding get() = _binding!!
    private var listing = mutableListOf<EventsToday>()

    private fun loadListing() {
        listing = mutableListOf(
            EventsToday("java", "great"),
            EventsToday("Kotlin Expo", "New York"),
            EventsToday("Python", "Atlanta"),
            EventsToday("JavaScript", "Georgia"),
            EventsToday("PHP", "California")

        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = EventsListFragmentBinding.inflate(inflater,container,false)
        val view = binding.root

        //binding.eventListViewModel
        //binding.lifecycleOwner = viewLifecycleOwner

        val adapter = TechEventAdapter(listing,object: TechEventAdapter.onItemClickListener{

            override fun onItemClick(itemView: View?, position: Int) {
               val eventtoday = listing[position].name

                val action = EventsListDirections.actionEventsList2ToEvents2()
                view.findNavController().navigate(action)

            }

        })

        // eventsView is the id of the RecyclerView in events_list_fragment xml
        binding.rcyview.layoutManager = LinearLayoutManager(requireContext())
        binding.rcyview.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadListing()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }




}