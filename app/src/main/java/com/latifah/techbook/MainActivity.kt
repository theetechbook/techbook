package com.latifah.techbook

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.latifah.techbook.databinding.ActivityMainBinding
import com.latifah.techbook.databinding.FragmentRegisterBinding
import com.latifah.techbook.ui.fragments.*
import dagger.hilt.android.AndroidEntryPoint

/**
 * An activity that inflates a layout that has a NavHostFragment.
 */

private lateinit var navController: NavController
private var _binding: ActivityMainBinding? = null
private val binding get() = _binding!!

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
//        setupActionBarWithNavController(navController)

        val dashFrag = Dashboard()
        val eventFrag = Events()
        val newPostFrag = NewPost()
        val likesFrag = Likes()
        val profileFrag = Profile()


        //buttons, actionables

        //top navigation

        //bottom navigation

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
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


    }



    fun setBottomNavigationVisibility(visibility: Int) {
        // get the reference of the bottomNavigationView and set the visibility.
      binding.bottomNavigationView.visibility = visibility
    }

    private fun setCurrentFragment(fragment: Fragment) =
        //needed due to nesting of Fragments
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.my_nav_host_fragment, fragment)
            commit()
        }

    }
