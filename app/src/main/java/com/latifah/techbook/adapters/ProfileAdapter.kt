package com.latifah.techbook.adaptersimport

import android.content.ClipData
import android.view.LayoutInflater
import com.latifah.techbook.R
import com.latifah.techbook.database.models.EventsToday
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.latifah.techbook.adapters.TechEventAdapter
import com.latifah.techbook.database.models.ProfileData
import kotlinx.android.extensions.LayoutContainer

class ProfileAdapter(
    private val exampleProfileList: List<ProfileData>,
    private val listener:TechEventAdapter.OnItemClickListener
) : RecyclerView.Adapter<ProfileAdapter.Viewholder>() {

   /* var data: List<ProfileData> = emptyList()
        set(newList) {
            val calculateDiff = DiffUtil.calculateDiff(DiffCallback(field, newList))
            calculateDiff.dispatchUpdatesTo(this)
            field = newList
        }

    */

    override fun getItemCount(): Int = exampleProfileList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.profile_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProfileAdapter.Viewholder, position: Int) {
       val currentItem = exampleProfileList[position]
        holder.imageView.setImageResource(currentItem.imageResour)
    }

    inner class Viewholder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
       View.OnClickListener{
        val imageView : ImageView = itemView.findViewById(R.id.profV)


        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }

    }
}

class DiffCallback(val oldList: List<ProfileData>, val newList: List<ProfileData>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]
        return old.id == new.id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }
}