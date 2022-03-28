package com.latifah.techbook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.latifah.techbook.R
import com.latifah.techbook.adapters.ContactAdapter
import com.latifah.techbook.adapters.TechEventAdapter
import com.latifah.techbook.database.models.ContactItem
import com.latifah.techbook.databinding.ContactItemLayoutBinding
import java.util.ArrayList
import kotlin.random.Random


class ContactFragment : Fragment(), ContactAdapter.OnItemClickListener,
    TechEventAdapter.OnItemClickListener {
    private var _binding: ContactItemLayoutBinding? = null
    private val binding get() = _binding!!
    private var dummyData = dummieData(30)
    private var contactPosts = mutableListOf<ContactItem>()
    //private val adapter = ContactAdapter(this.dummyData)
   var bottomNavigationViewVisibility = View.VISIBLE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContactItemLayoutBinding.inflate(inflater, container, false)
        val view = binding.root


        dummyData = dummieData(50)

        val adapter = ContactAdapter(dummieData(20),this)
        binding.contactRcyV.adapter = adapter
        binding.contactRcyV.layoutManager = LinearLayoutManager(requireContext())
        binding.contactRcyV.setHasFixedSize(true)

        /*
         val recyclerView = view.findViewById<RecyclerView>(R.id.contact_rcyV)
         recyclerView.adapter = adapter
         recyclerView.layoutManager = LinearLayoutManager(requireContext())
         recyclerView.setHasFixedSize(true)

         */


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contactItem = dummieData(20)


    }

    private fun dummieData(size: Int): MutableList<ContactItem> {
        val list = ArrayList<ContactItem>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_person_icon
                1 -> R.drawable.ic_contact_person
                else -> R.drawable.ic_navigate_next
            }
            val item = ContactItem(drawable, 1, "Item $i", "Line 2")
            list += item
        }
        return list
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(context, "item $position clicked", Toast.LENGTH_LONG).show()
        val clickItem = dummyData[position]

        val action = ContactFragmentDirections.actionContactFragment2ToContactReceiverFragment(
            clickItem.text1,clickItem.text2
        )
        findNavController().navigate(action)

    }



    fun insertItem(view: View){
           val index = Random.nextInt()
           val newItem = ContactItem(100,R.drawable.techiehumor,"Test","email")
           dummyData.add(index,newItem)

       }

       fun removeItem(view: View){
           val index: Int = Random.nextInt()
           dummyData.removeAt(index)

       }



    // override fun invoke(p1: ContactItem) {
    //
  //  }
}


