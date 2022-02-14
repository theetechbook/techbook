package com.latifah.techbook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.latifah.techbook.R
import com.latifah.techbook.database.models.ContactItem


class ContactFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.contact_item_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contactItem = dummieData(20)
    }

    private fun dummieData(size:Int):List<ContactItem> {
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
}