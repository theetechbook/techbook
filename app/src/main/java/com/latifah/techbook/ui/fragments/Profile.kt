package com.latifah.techbook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavArgs
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.latifah.techbook.R
import com.latifah.techbook.adaptersimport.ProfileAdapter
import com.latifah.techbook.database.models.DataSource
import com.latifah.techbook.database.models.EventsToday
import com.latifah.techbook.database.models.ProfileData
import com.latifah.techbook.databinding.FragmentProfileBinding

/**
 * A simple [Fragment] subclass.
 * Use the [Profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class Profile : Fragment(), ProfileAdapter.OnItemClickListener {
   // var bottomNavigationViewVisibility = View.VISIBLE
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var dummieData = dummieProfile(44)
    private val args: ProfileArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding?.root
        //  binding.messageProfile.setOnClickListener {
        //    val action = ProfileDirections.actionProfile2ToEventsList()
        //   findNavController().navigate(action)
        //   }

        val adapter = ProfileAdapter(dummieProfile(6),this)
        val recyclerView = binding.profilerecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.setHasFixedSize(true)

        return view

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
         binding.username.text = args.userName
         binding.name.text = "${args.firstName}  ${args.lastName}"

    }



    private fun dummieProfile(size: Int): ArrayList<ProfileData> {
        val list = ArrayList<ProfileData>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.techiehumor
                1 -> R.drawable.techiehumor
                else -> R.drawable.techiehumor
            }
            val item = ProfileData(
                1,
                2,
                "info@email.com",
                "John Dow",
                "Testing Description",
            "test"
            )
            list += item
        }
        return list
    }


    override fun onItemClick(position: Int) {
        Toast.makeText(context, "item $position clicked", Toast.LENGTH_SHORT).show()
        val clickItem = dummieProfile(6)
    }

    //binding.saveProfileButton.setOnClickListener {
    //    open EditProfileTextFragment with Intent(?)
    // }

    //This is the code for the button to "log out".
    // binding.logoutButton.setOnClickListener {
    //private fun logout (?)
    // }

    override fun onDestroy() {
        super.onDestroy()
         _binding = null
    }
}