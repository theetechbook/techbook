package com.latifah.techbook.adapters

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.latifah.techbook.R
import com.latifah.techbook.database.models.ContactItem

class ContactAdapter(private val exampleList: ArrayList<ContactItem>,
                     private val myListener: OnItemClickListener
                     ) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
   inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        // viewholder represents a single row in our list, one instance of our row
        // it holds one instance of our views that we created our con item layout


        val imageView: ImageView = itemView.findViewById(R.id.imagV)
        val textView1: TextView = itemView.findViewById(R.id.con_txt)
        val textView2: TextView = itemView.findViewById(R.id.con_subtxt)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
           val position: Int = adapterPosition
           if (position != RecyclerView.NO_POSITION){
               myListener.onItemClick(position)
           }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.ContactViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)
        return ContactViewHolder(item)
    }

    override fun onBindViewHolder(holder: ContactAdapter.ContactViewHolder, position: Int) {
        val currentItem = exampleList[position]
        holder.imageView.setImageResource(currentItem.imageResour)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2
    }

    override fun getItemCount(): Int {
        return exampleList.size
    }

}