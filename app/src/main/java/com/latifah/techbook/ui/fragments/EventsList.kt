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

class EventsList : Fragment(), TechEventAdapter.OnItemClickListener {
    private var _binding: EventsListFragmentBinding? = null
    private val binding get() = _binding!!
    private var dummieData = dummieEvent(0)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = EventsListFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        val myDataset = DataSource().loadEvents()
        dummieData = dummieEvent(50)
        val adapter = TechEventAdapter(dummieData,this)
        binding.rcyview.adapter = adapter
        binding.rcyview.layoutManager = LinearLayoutManager(requireContext())


        return view
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun dummieEvent(size:Int):ArrayList<EventsToday> {
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

    override fun onItemClick(position: Int) {
        Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickItem = dummieData[position]

        //notifyItemChanged(position)
        val action = EventsListDirections.actionEventsListToEvents(clickItem.name,clickItem.location)
        findNavController().navigate(action)
    // clickItem.
    }

}









