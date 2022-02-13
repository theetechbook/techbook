package com.latifah.techbook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.latifah.techbook.R
import com.latifah.techbook.adapters.TechEventAdapter.ViewHolder
import com.latifah.techbook.database.models.EventsToday

class TechEventAdapter( val elist:MutableList<EventsToday>, private val myListener:onItemClickListener) : RecyclerView.Adapter<ViewHolder>() {


    // Created a New Interface OnItemClick Listener

   interface onItemClickListener{
       fun onItemClick(index:Int)

    }


    inner class ViewHolder(itemView: View, listener:TechEventAdapter.onItemClickListener): RecyclerView.ViewHolder(itemView){
        val eventName = itemView.findViewById<TextView>(R.id.txt)
        val eventLocation = itemView.findViewById<TextView>(R.id.sub_txt)
        init{
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val dataItemView = inflater.inflate(R.layout.item, parent,false)
        return ViewHolder(dataItemView,myListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val eventList = elist.get(position)
        holder.eventName.text = eventList.name
        holder.eventLocation.text = eventList.location

    }

    override fun getItemCount(): Int {
      return  elist.size
    }

}