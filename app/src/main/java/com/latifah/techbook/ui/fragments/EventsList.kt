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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.util.WorkSourceUtil.add
import com.latifah.techbook.adapters.TechEventAdapter
import com.latifah.techbook.database.models.DataSource
import com.latifah.techbook.database.models.EventsToday
import com.latifah.techbook.databinding.EventsListFragmentBinding
import com.latifah.techbook.ui.viewmodels.EventsListViewModel
import org.intellij.lang.annotations.Language

class EventsList : Fragment() {
    private var _binding: EventsListFragmentBinding? = null
    private val binding get() = _binding!!
    private val dataset = mutableListOf<EventsToday>()

    //private var listing = mutableListOf<EventsToday>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = EventsListFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        val myDataset = DataSource().loadEvents()


        val adapter = TechEventAdapter(myDataset, object :
            TechEventAdapter.OnItemClickListener {

            fun onItemClick(itemView: View?, position: Int) {
                val data = myDataset[position]
                binding.root?.setOnClickListener {
                     //val action = EventsListDirections.actionEventsList2ToEvents2()
                    //itemView?.findNavController()?.navigate(action)
                    //Log.i("ana","button clicked")


                }
            }

            override fun onItemClick(position: Int) {
                TODO("Not yet implemented")
            }

        })

        // eventsView is the id of the RecyclerView in events_list_fragment xml
        binding.rcyview.layoutManager = LinearLayoutManager(requireContext())
        binding.rcyview.adapter = adapter
        return view
    }

   /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.setOnClickListener(
            send()
        )

    }

    */

    private fun send(index:Int) {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}



