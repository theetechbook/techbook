package com.latifah.techbook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.latifah.techbook.R
import com.latifah.techbook.adapters.TechEventAdapter.ViewHolder
import com.latifah.techbook.database.models.EventsToday

class TechEventAdapter( val elist:MutableList<EventsToday>, private var myListener:onItemClickListener) : RecyclerView.Adapter<ViewHolder>() {


    // Created a New Interface OnItemClick Listener

   interface onItemClickListener{
       fun onItemClick(itemView: View?,position: Int)

    }
fun setOnItemClickListener(mylistener: AdapterView.OnItemClickListener){
    this.myListener = myListener
}

    inner class ViewHolder(itemView: View, listener:TechEventAdapter.onItemClickListener): RecyclerView.ViewHolder(itemView){
        val eventName = itemView.findViewById<TextView>(R.id.txt)
        val eventLocation = itemView.findViewById<TextView>(R.id.sub_txt)
        init{
            itemView.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION)
                listener.onItemClick(itemView,position)
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