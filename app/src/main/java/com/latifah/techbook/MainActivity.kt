package com.latifah.techbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.latifah.techbook.databinding.ActivityMainBinding
import com.latifah.techbook.ui.fragments.*
import dagger.hilt.android.AndroidEntryPoint

/**
 * An activity that inflates a layout that has a NavHostFragment.
 */
@AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val builder = AppBarConfiguration.Builder(navController.graph)
        val appBarConfiguration = builder.build()

        toolbar.setupWithNavController(navController, appBarConfiguration)

        setupActionBarWithNavController(navController)

        val dashFrag = Dashboard()
        val eventListFrag = EventsList()
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
                R.id.menu_eventsList -> {
                    setCurrentFragment(eventListFrag)
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

    fun setCurrentFragment(fragment: Fragment) =
        //needed due to nesting of Fragments
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.my_nav_host_fragment, fragment)
            commit()
        }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_bar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.my_nav_host_fragment)
        return item.onNavDestinationSelected(navController) ||
        super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

