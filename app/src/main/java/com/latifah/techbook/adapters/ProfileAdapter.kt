package com.latifah.techbook.adaptersimport

import android.view.LayoutInflater
import com.latifah.techbook.R
import com.latifah.techbook.database.models.EventsToday
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.latifah.techbook.database.models.ProfileData
import kotlinx.android.extensions.LayoutContainer

class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.Viewholder>() {

    var data: List<ProfileData> = emptyList()
        set(newList) {
            val calculateDiff = DiffUtil.calculateDiff(DiffCallback(field, newList))
            calculateDiff.dispatchUpdatesTo(this)
            field = newList
        }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.profile_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProfileAdapter.Viewholder, position: Int) {
        holder.bind(data[position])
    }

    inner class Viewholder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(item:) = with(itemView) {

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