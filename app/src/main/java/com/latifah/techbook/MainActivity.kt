package com.latifah.techbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.latifah.techbook.R.id
import com.latifah.techbook.databinding.ActivityMainBinding
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

        val toolbar = binding.toolbar
        //val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        val navHostFragment =
            supportFragmentManager.findFragmentById(id.my_nav_host_fragment) as NavHostFragment
       val  navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

      /*navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if(destination.id == (R.id.profile)) {
                    toolbar.visibility = View.GONE
                   binding.bottomNavigationView.visibility = View.GONE
                } else {
                toolbar.visibility = View.VISIBLE
                binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }

       */

        navController.addOnDestinationChangedListener { _, _, arguments ->
           binding.bottomNavigationView.isVisible = arguments?.getBoolean("ShowAppBar", false) == false
        }

        val builder = AppBarConfiguration.Builder(navController.graph)
        val appBarConfiguration = builder.build()

       toolbar.setupWithNavController(navController, appBarConfiguration)

        setupActionBarWithNavController(navController)

        val homeFrag = Homepage()
        val eventListFrag = EventsList()
        val newPostFrag = NewPost()
        val likesFrag = Likes()
        val profileFrag = Profile()


        //buttons, actionables

        //top navigation

        //bottom navigation

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                id.menu_home -> {
                    setCurrentFragment(homeFrag)
                    Log.i("Robin", "Navigated to Home Fragment")
                    true
                }
                id.menu_eventsList -> {
                    setCurrentFragment(eventListFrag)
                    Log.i("Robin", "Navigated to Events Lists Fragment")
                    true
                }
                id.menu_new -> {
                    setCurrentFragment(newPostFrag)
                    Log.i("Robin", "Navigated to New Fragment")
                    true
                }
                id.menu_likes2 -> {
                    setCurrentFragment(likesFrag)
                    Log.i("Robin", "Navigated to Likes Fragment")
                    true
                }
                id.menu_profile2 -> {
                    //val action = Activit.actionLoginToProfile2("Test","test2","test3")
                   // findNavController().navigate(action)
                    //loginSuccess()
                    setCurrentFragment(profileFrag)
                    Log.i("Robin", "Navigated to Profile Fragment")
                    true
                }
                else -> false
            }
        }
        // return
    }

/*
    fun loginSuccess( ) {
        val action = LoginDirections.actionLoginToProfile2("Test","test2","test3")
        findNavController().navigate(action)
    }
*/
private fun setupBottomNavMenu(navController: NavController) {
    val bottomNav = findViewById<BottomNavigationView>(R.id.my_nav_host_fragment)
    bottomNav?.setupWithNavController(navController)
}

    fun setBottomNavigationVisibility(visibility: Int) {
        // get the reference of the bottomNavigationView and set the visibility.
        binding.bottomNavigationView.visibility = visibility
    }



    fun setCurrentFragment(fragment: Fragment) =
        //needed due to nesting of Fragments
        supportFragmentManager.beginTransaction().apply {
            replace(id.my_nav_host_fragment, fragment)
            commit()
        }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_bar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(id.my_nav_host_fragment)
        return item.onNavDestinationSelected(navController) ||
        super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

