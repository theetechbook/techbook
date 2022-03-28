package com.latifah.techbook.ui.fragments


import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.latifah.techbook.MainActivity
import com.latifah.techbook.R
import com.latifah.techbook.adapters.TechEventAdapter
import com.latifah.techbook.database.models.EventsToday
import com.latifah.techbook.databinding.EventsListFragmentBinding
import com.latifah.techbook.network.Event
import com.latifah.techbook.ui.viewmodels.EventsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsList : Fragment(), TechEventAdapter.OnItemClickListener {
    private var _binding: EventsListFragmentBinding? = null
    private val binding get() = _binding
  private lateinit var navController: NavController
    private var dummieData = dummieEvent(0)
    private val viewModel: EventsListViewModel by viewModels()
    private val args: EventsListArgs by navArgs()
    private val events : MutableList<Event> = mutableListOf()

   // var bottomNavigationViewVisibility = View.VISIBLE

    override fun onCreate(savedInstanceState: Bundle?) {
        navController = findNavController(requireActivity(), R.id.my_nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        val toolbar = view?.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.setupWithNavController(navController, appBarConfiguration)
        Log.i("test2", "test3")
       // navController = this.findNavController()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.events_list_fragment,container,false)
        _binding = EventsListFragmentBinding.inflate(inflater, container, false)
        val view = binding?.root
        Log.d(" onCreateView", "preparing to observe")
//        viewModel.locationLiveData.observe(viewLifecycleOwner) {
//            viewModel.setLocation(it).value?.let { it1 -> viewModel.getEvents(it1) }
//        }
//

        val location = args.location
        if (location != null) {
            viewModel.getEvents(location)
        }
        Log.d("eventLocation", "$location")

        //val myDataset = DataSource().loadEvents()
        dummieData = dummieEvent(50)

/*
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        val toolbar = view?.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.setupWithNavController(navController, appBarConfiguration)
        Log.i("test2", "test3")

 */
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel.checkEventsList()
        viewModel.eventsList.observe(viewLifecycleOwner) {

            Log.d(" observing eventsList", "${it?.size}")
            if (it != null && it.isNotEmpty()) {
                for (event in it) {
                    Log.d("events from PredictHQ", "${event?.title}")
                    event?.let { eventItem -> events.add(eventItem) }
                }
            }
            val adapter = TechEventAdapter(events, this)
            binding?.rcyview?.adapter = adapter
            binding?.rcyview?.layoutManager = LinearLayoutManager(requireContext())
            binding?.rcyview?.setHasFixedSize(true)
        }
    }


/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         navController = findNavController( )
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        Log.i("test2", "test3")
    }

 */


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun dummieEvent(size: Int): ArrayList<EventsToday> {
        val list = ArrayList<EventsToday>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_person_icon
                1 -> R.drawable.ic_contact_person
                else -> R.drawable.ic_navigate_next
            }
            val item = EventsToday(
                1,
                "Item $i",
                "Line 2",
                10,
                3 / 12 / 22,
                "online",
                "image",
                "description"
            )
            list += item
        }
        return list
    }

    override fun onItemClick(position: Int) {

        Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickItem = events[position]
        Log.i("ana", "clicked on $clickItem")
        //notifyItemChanged(position)
        val action = EventsListDirections.actionEventsListToEvent(
            clickItem.title,
            clickItem.start,
            clickItem.end,
            clickItem.description,
            clickItem.scope,
            clickItem.timezone
        )
        findNavController()?.navigate(action)
        // clickItem.

    }


}



