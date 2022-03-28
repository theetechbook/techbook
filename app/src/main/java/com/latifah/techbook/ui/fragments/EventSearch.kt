package com.latifah.techbook.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.latifah.techbook.R
import com.latifah.techbook.databinding.FragmentEditProfileBinding
import com.latifah.techbook.databinding.FragmentEventSearchBinding
import com.latifah.techbook.ui.viewmodels.EventsListViewModel
import com.latifah.techbook.ui.viewmodels.TechbookViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [Dashboard.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class EventSearch : BaseFragment() {

    private val viewModel: EventsListViewModel by viewModels()


    override var bottomNavigationViewVisibility = View.VISIBLE
    private var _binding: FragmentEventSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEventSearchBinding.inflate(inflater, container, false)
        viewModel.getEvents()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.eventsList.observe(viewLifecycleOwner) {
            if (it != null) {
                Log.d("events from PredictHQ", "${it[0]?.title}")
            }

        }
    }
}