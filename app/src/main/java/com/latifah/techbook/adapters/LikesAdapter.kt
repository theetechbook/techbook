package com.latifah.techbook.adaptersimport

import android.view.LayoutInflater
import com.latifah.techbook.R
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.latifah.techbook.adapters.TechEventAdapter
import com.latifah.techbook.database.models.LikesData
import kotlinx.android.extensions.LayoutContainer

class LikesAdapter(private val likesList: List<LikesData>, private val listener:TechEventAdapter.OnItemClickListener) : RecyclerView.Adapter<LikesAdapter.Viewholder>() {


    override fun getItemCount(): Int {
        return likesList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.likes_item, parent, false)
        return Viewholder(itemView)
    }

    override fun onBindViewHolder(holder: LikesAdapter.Viewholder, position: Int) {
        val currentItem = likesList[position]



    }

    inner class Viewholder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }


}
