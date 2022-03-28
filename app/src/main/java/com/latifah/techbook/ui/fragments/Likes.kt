package com.latifah.techbook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.latifah.techbook.R
import com.latifah.techbook.adaptersimport.LikesAdapter
import com.latifah.techbook.database.models.LikesData
import com.latifah.techbook.databinding.LikesListLayoutBinding
import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 * Use the [Likes.newInstance] factory method to
 * create an instance of this fragment.
 */
class Likes : BaseFragment(), LikesAdapter.OnItemClickListener {
    private var _binding : LikesListLayoutBinding? = null
    private val binding get() = _binding!!
    private var dummyData = dummieData(30)

    override var bottomNavigationViewVisibility = View.VISIBLE


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = LikesListLayoutBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        val view = binding.root

        dummyData = dummieData(20)

        val adapter = LikesAdapter(dummieData(10),this, context)
       binding?.likesPhotosRcyview?.adapter = adapter
        binding?.likesPhotosRcyview?.layoutManager = GridLayoutManager(context,2, RecyclerView.VERTICAL, false)
       binding?.likesPhotosRcyview.setHasFixedSize(true)
        return view;
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun dummieData(size: Int): MutableList<LikesData> {
        val list = ArrayList<LikesData>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_person_icon
                1 -> R.drawable.ic_contact_person
                else -> R.drawable.ic_navigate_next
            }
            val item = LikesData(drawable, "string", "Item $i", "Line 2","line 3","line4")
            list += item
        }
        return list
    }

    override fun onItemClick(position: Int) {
        val clickLikesItem = dummyData[position]
    }
}