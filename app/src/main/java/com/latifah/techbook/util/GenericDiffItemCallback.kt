package com.latifah.techbook.utilimport

import android.view.LayoutInflater
import com.latifah.techbook.database.models.EventsToday
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

class GenericDiffItemCallback(val oldList: List<EventsToday>, val newList: List<EventsToday>) : DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                return false
            }

            oldList[oldItemPosition].name != newList[newItemPosition].name -> {
                return false
            }

            oldList[oldItemPosition].location != newList[newItemPosition].location -> {
                return false
            }
            else -> true

        }
    }
}






