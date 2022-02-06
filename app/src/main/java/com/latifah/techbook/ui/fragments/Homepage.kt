package com.latifah.techbook.ui.fragments

import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.latifah.techbook.R

/**
 * A simple [Fragment] subclass.
 * Use the [Homepage.newInstance] factory method to
 * create an instance of this fragment.
 */

class Homepage : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // initialize variable, inflate layout
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //fragment variables
        val dashFrag = Dashboard()
        val eventFrag = Events()
        val newPostFrag = NewPost()
        val likesFrag = Likes()
        val profileFrag = Profile()

        // This line of code was making app crash upon loading, but the fragments are still overlapping?
        // setCurrentFragment(Homepage())

        //buttons, actionables

        //top navigation

        //bottom navigation
        view.findViewById<BottomNavigationView>(R.id.bottom_nav).setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    setCurrentFragment(dashFrag)
                    Log.i("Robin", "Navigated to Home Fragment")
                    true
                }
                R.id.menu_events -> {
                    setCurrentFragment(eventFrag)
                    Log.i("Robin", "Navigated to Events Fragment")
                    true
                }
                R.id.menu_new -> {
                    setCurrentFragment(newPostFrag)
                    Log.i("Robin", "Navigated to New Fragment")
                    true
                }
                R.id.menu_likes -> {
                    setCurrentFragment(likesFrag)
                    Log.i("Robin", "Navigated to Likes Fragment")
                    true
                }
                R.id.menu_profile -> {
                    setCurrentFragment(profileFrag)
                    Log.i("Robin", "Navigated to Profile Fragment")
                    true
                }
                else -> false
            }
        }
        // return
        return view
    }

    //move to ViewModel?
    private fun setCurrentFragment(fragment: Fragment) =
        //needed due to nesting of Fragments
        childFragmentManager.beginTransaction().apply {
            replace(R.id.nav_fragment, fragment)
            commit()
        }
}