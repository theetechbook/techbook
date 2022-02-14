package com.latifah.techbook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.latifah.techbook.R
import com.latifah.techbook.database.models.ContactItem
import com.latifah.techbook.database.models.EventsToday

class TechEventAdapter(private val exampleList: List<EventsToday>) : RecyclerView.Adapter<TechEventAdapter.TechViewHolder>() {


    class TechViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // viewholder represents a single row in our list, one instance of our row
        // it holds one instance of our views that we created our con item layout
        //val imageView: ImageView = itemView.findViewById(R.id.txt)
        val textView1: TextView = itemView.findViewById(R.id.txt)
        val textView2: TextView = itemView.findViewById(R.id.sub_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechEventAdapter.TechViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return TechViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return exampleList.size
    }

    override fun onBindViewHolder(holder: TechViewHolder, position: Int) {
        val currentItem = exampleList[position]
        holder.textView1.text = currentItem.name
        holder.textView2.text = currentItem.location    }


}





