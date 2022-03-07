package com.latifah.techbook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.latifah.techbook.R
import com.latifah.techbook.databinding.FragmentContactReceiverBinding


class ContactReceiverFragment : Fragment() {
    private var _binding: FragmentContactReceiverBinding? = null
    private val binding get() = _binding!!
    private val args: ContactReceiverFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_contact_receiver, container, false)
    _binding = FragmentContactReceiverBinding.inflate(inflater,container,false)

        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView2.text = args.contactName
        binding.textView3.text = args.contactEmail
    }


}


