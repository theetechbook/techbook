package com.latifah.techbook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.latifah.techbook.R


/**
 * A simple [Fragment] subclass.
 * Use the [Likes.newInstance] factory method to
 * create an instance of this fragment.
 */
class Likes : BaseFragment() {

    override var bottomNavigationViewVisibility = View.VISIBLE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_likes, container, false)
    }
}