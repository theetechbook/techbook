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
import androidx.fragment.app.FragmentManager
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



@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomView = binding.bottomNavigationView
        val toolbar = binding.toolbar
        //val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        //these lines get  a reference to the navigation controller from the navigation host
        val navHostFragment =
            supportFragmentManager.findFragmentById(id.my_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomView.setupWithNavController(navController)


        //app bar configuration - helps app bar & tool bar work well with navigation controller
        // Line 73 & 74 builds a configuration linking the toolbar to the navigation graphy
        //val builder = AppBarConfiguration.Builder(navController.graph)
        //val appBarConfiguration = builder.build()

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homepage, R.id.eventSearch,R.id.newPost3,R.id.profile2, R.id.likes2))

        toolbar.setupWithNavController(navController, appBarConfiguration)

       setupActionBarWithNavController(navController)
       bottomView.setupWithNavController(navController)

 }


 fun loginSuccess() {
         val action = LoginDirections.actionLoginToProfile2()
         findNavController(R.id.my_nav_host_fragment).navigate(action)
     }


     fun setupBottomNavMenu(navController: NavController) {
         val bottomNav = findViewById<BottomNavigationView>(R.id.my_nav_host_fragment)
         bottomNav?.setupWithNavController(navController)
     }

     fun setBottomNavigationVisibility(visibility: Int) {
         // get the reference of the bottomNavigationView and set the visibility.
         binding.bottomNavigationView.visibility = visibility
     }

    fun setToolbarVisibility(visibility: Int) {
        // get the reference of the bottomNavigationView and set the visibility.
        binding.toolbar.visibility = visibility
    }

     fun setCurrentFragment(fragment: Fragment) =
         //needed due to nesting of Fragments
         supportFragmentManager.beginTransaction().apply {
             replace(id.my_nav_host_fragment, fragment)
             commit()
             true
         }


     override fun onCreateOptionsMenu(menu: Menu): Boolean {
         menuInflater.inflate(R.menu.top_bar, menu)
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

