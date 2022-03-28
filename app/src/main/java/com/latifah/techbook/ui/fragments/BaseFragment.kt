package com.latifah.techbook.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.latifah.techbook.MainActivity

abstract class BaseFragment: Fragment() {

    protected open var bottomNavigationViewVisibility = View.VISIBLE
    protected open var toolbarVisibility = View.VISIBLE
    protected open var userFirstName: String? = ""
    protected open var lastName = ""
    protected open var userName = ""

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // get the reference of the parent activity and call the setBottomNavigationVisibility method.
        if (activity is MainActivity) {
            var  mainActivity = activity as MainActivity
          //  mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
        }
    }
    override fun onResume() {
        super.onResume()
        if (activity is MainActivity) {
            var  mainActivity = activity as MainActivity
           // mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
        }
    }

//    fun setCurrentUserFirstName(firstName: String?) {
//        userFirstName = firstName
//        Log.d("userFirstName is now", "$userFirstName")
//    }
}