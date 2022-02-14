package com.latifah.techbook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.latifah.techbook.R
import com.latifah.techbook.adapters.ContactAdapter
import com.latifah.techbook.database.models.ContactItem
import kotlin.random.Random


class ContactFragment : Fragment() {

    private val dummyData = dummieData(30)
    private val adapter = ContactAdapter(this.dummyData)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.contact_item_layout, container, false)


       //val adapter = ContactAdapter(list)
        val recyclerView = view.findViewById<RecyclerView>(R.id.contact_rcyV)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contactItem = dummieData(20)



    }

    private fun dummieData(size:Int):ArrayList<ContactItem> {
        val list = ArrayList<ContactItem>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_person_icon
                1 -> R.drawable.ic_contact_person
                else -> R.drawable.ic_navigate_next
            }
            val item = ContactItem(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }

    fun insertItem(view: View){
        val index = Random.nextInt(8)
        val newItem = ContactItem(
            R.drawable.ic_contact_person,
            "Person Last Name at Position $index",
            "Email"
        )
        dummyData.add(index,newItem)
        adapter.notifyItemInserted(index)
    }

    fun removeItem(view: View){
        val index: Int = Random.nextInt(8)
        dummyData.removeAt(index)
        adapter.notifyItemRemoved(index)

    }
}