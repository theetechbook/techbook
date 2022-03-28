package com.latifah.techbook.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.latifah.techbook.R
import com.latifah.techbook.adapters.TechEventAdapter
import com.latifah.techbook.adaptersimport.HomeAdapter
import com.latifah.techbook.database.models.HomeData
import com.latifah.techbook.database.models.Post
import com.latifah.techbook.databinding.FragmentHomeBinding
import com.latifah.techbook.ui.viewmodels.TechbookViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [Homepage.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class Homepage : BaseFragment(), HomeAdapter.OnItemClickListener,
    TechEventAdapter.OnItemClickListener {

    private val viewModel: TechbookViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var dummyData = homeDumData(30)
    override var bottomNavigationViewVisibility = View.VISIBLE
    private var homePosts = mutableListOf<Post?>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // initialize variable, inflate layout
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.getAllPosts()
        viewModel.posts.observe(viewLifecycleOwner) {
            if (it != null) {
                for (post in it) {
                    homePosts.add(post)
                    Log.d(
                        "Observing posts from Firebase",
                        "${post?.gif_url} \n" +
                                "${post?.text} \n" +
                                "${post?.userUid} \n" +
                                "${post?.username}"
                    )
                }

                Log.d("setting up Home Adapter", "${homePosts[0]?.gif_url}")
                val adapter = this.context?.let { context -> HomeAdapter(homePosts, this, context) }
                binding.homeRcyV.adapter = adapter
                binding.homeRcyV.layoutManager = LinearLayoutManager(requireContext())
                binding.homeRcyV.setHasFixedSize(true)

            }
        }











        return view
    }

    private fun homeDumData(size: Int): ArrayList<HomeData> {
        val list = ArrayList<HomeData>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.happy
                1 -> R.drawable.happy
                else -> R.drawable.ic_navigate_next
            }
            val item = HomeData(drawable, 1.toString(), "Item $i", "Item $i","","test","test2", "test3","",)
            list += item
        }
        return list
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(context, "item $position clicked", Toast.LENGTH_LONG).show()
        val clickItem = dummyData[position]
    }

//    fun setupRecyclerView(list: MutableList<String?>, listener: HomeAdapter.OnItemClickListener) {
//        val adapter = HomeAdapter(list, listener)
//        binding.homeRcyV.adapter = adapter
//        binding.homeRcyV.layoutManager = LinearLayoutManager(requireContext())
//        binding.homeRcyV.setHasFixedSize(true)
//    }
}