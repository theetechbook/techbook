package com.latifah.techbook.adapters

import android.graphics.Color
import android.graphics.Color.YELLOW
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.latifah.techbook.R
import com.latifah.techbook.database.models.ContactItem
import com.latifah.techbook.database.models.EventsToday
import com.latifah.techbook.network.Event

class TechEventAdapter(
    private val exampleList: MutableList<Event>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<TechEventAdapter.TechViewHolder>() {


   inner class TechViewHolder(itemView: View, ) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
       // viewholder represents a single row in our list, one instance of our row
       // it holds one instance of our views that we created our con item layout
       //val imageView: ImageView = itemView.findViewById(R.id.txt)
       val textView1: TextView = itemView.findViewById(R.id.txt)
       val textView2: TextView = itemView.findViewById(R.id.sub_txt)


       init {
           //This refers to the TechViewHolder itself
           itemView.setOnClickListener(this)
       }

       override fun onClick(v: View?) {
           val position: Int = adapterPosition
           if (position != RecyclerView.NO_POSITION) {
               listener.onItemClick(position)
           }

       }
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
        holder.textView1.text = currentItem.title
        holder.textView2.text = currentItem.timezone

        //if (position == 0){
         //   holder.textView1.setBackgroundColor(Color.CYAN)
        //}
    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}





