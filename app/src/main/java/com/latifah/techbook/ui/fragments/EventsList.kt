package com.latifah.techbook.ui.fragments


import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.latifah.techbook.R
import com.latifah.techbook.R.id.my_nav_host_fragment
import com.latifah.techbook.adapters.TechEventAdapter
import com.latifah.techbook.database.models.DataSource
import com.latifah.techbook.database.models.EventsToday
import com.latifah.techbook.databinding.EventsListFragmentBinding

class EventsList : Fragment(), TechEventAdapter.OnItemClickListener {
    private var _binding: EventsListFragmentBinding? = null
    private val binding get() = _binding
    private var dummieData = dummieEvent(0)
   // private lateinit var localController: NavController
    var bottomNavigationViewVisibility = View.VISIBLE


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.events_list_fragment,container,false)
        _binding = EventsListFragmentBinding.inflate(inflater, container, false)
        val view = binding?.root

        //val myDataset = DataSource().loadEvents()
        dummieData = dummieEvent(50)
        val adapter = TechEventAdapter(dummieData, this)

         //navController = binding.container.getFragment<NavHostFragment>().navController
       // val localNavHost = childFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
      //  localController = localNavHost.navController
        // val recyclerView = view?.findViewById<RecyclerView>(R.id.rcyview)
        //val recyclerView = binding.rcyview
        binding?.rcyview?.adapter = adapter
        binding?.rcyview?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rcyview?.setHasFixedSize(true)

        return view
    }


    override fun onDestroy() {
        super.onDestroy()
        // _binding = null
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
        val clickItem = dummieData[position]

        //notifyItemChanged(position)
        val action = EventsListDirections.actionEventsListToEvent(
            clickItem.name, clickItem.location,
            clickItem.time.toString(), clickItem.description, clickItem.image, clickItem.online
        )
        view?.findNavController()?.navigate(action)
        // clickItem.
    }

}









