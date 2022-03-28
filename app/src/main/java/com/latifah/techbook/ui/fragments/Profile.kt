package com.latifah.techbook.ui.fragments

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
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.latifah.techbook.R
import com.latifah.techbook.adaptersimport.HomeAdapter
import com.latifah.techbook.adaptersimport.ProfileAdapter
import com.latifah.techbook.database.models.*
import com.latifah.techbook.databinding.FragmentProfileBinding
import com.latifah.techbook.ui.viewmodels.TechbookViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [Profile.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class Profile : BaseFragment(), ProfileAdapter.OnItemClickListener {

   // var bottomNavigationViewVisibility = View.VISIBLE
    private val viewModel: TechbookViewModel by viewModels()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var dummieData = dummieProfile(44)
    private val args: ProfileArgs by navArgs()
    private var profilePosts = mutableListOf<Post?>()

    // private var lastName = ""
    // private var userName = ""


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
        //viewModel.setCurrentUserFirstName(this)
        //Logging email for now, but will log username, first name, and last name when possible


       // var keysArrayList =
            //viewModel.getCurrentUserLastName()
//        for (item in keysArrayList) {
//            Log.d("getUserDisplayKeys", "$item")
//        }
        //viewModel.getCurrentUserFirstName(viewModel, this)
        viewModel.getUserInfo()
        viewModel.getPostByUserId()
        viewModel.userPosts.observe(viewLifecycleOwner) {
            if (it != null) {
                for (post in it) {
                    profilePosts.add(post)
                    Log.d(
                        "Observing user posts from Firebase",
                        "${post?.gif_url} \n" +
                                "${post?.text} \n" +
                                "${post?.userUid} \n" +
                                "${post?.username}"
                    )
                }
                val adapter = this.context?.let { context -> ProfileAdapter(profilePosts, this, context) }
                val recyclerView = binding.profilerecyclerView
                recyclerView.adapter = adapter
                recyclerView.layoutManager = GridLayoutManager(context, 3)
                recyclerView.setHasFixedSize(true)

            }
        }

        return view

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        //val userName = viewModel.getCurrentUserName()
        //val firstName = viewModel.getCurrentUserFirstName()

        //val lastName = viewModel.getCurrentUserLastName()

        // binding.username.text = userName
//        viewModel.userFirstName.observe(viewLifecycleOwner, {
//            Log.d("firstName", "$it")
//            firstName = it
//            binding.name.text = firstName
//        })

//        viewModel.userFirstName.observe(viewLifecycleOwner, {
//            Log.d("LiveData firstName is", "$it")
//        }
//        )
        // val firstName = super.userFirstName
        //Log.d("profileFrag firstName is", "$firstName")
        //binding.name.text = viewModel.getCurrentUserFirstName(viewModel, this)
        viewModel.userInfo.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.name.text = "${it.firstName} ${it.lastName}"
                binding.username.text = "${it.userName}"
            }

        }

        binding.btnEditProfile.setOnClickListener {
            val action = ProfileDirections.actionProfile2ToEditProfileFragment()
            findNavController().navigate(action)
        }
    }

    fun setUserInfo(user: User) {
        //firstName = user.firstName
        lastName = user.lastName
        userName = user.userName
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

    fun setCurrentUserFirstName(firstName: String?, lastName: String?, userName: String?) {
        binding.name.text = "$firstName $lastName"
        binding.username.text = "$userName"
        Log.d("userFirstName is now", "$firstName")
    }
}