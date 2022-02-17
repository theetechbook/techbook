package com.latifah.techbook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.latifah.techbook.R
import com.latifah.techbook.database.models.PostData

class ProfileAdapter (private val profileList: ArrayList<PostData>) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

        class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            // viewholder represents a single row in our list, one instance of our row
            // it holds one instance of our views that we created our con item layout
            val imageView: ImageView = itemView.findViewById(R.id.postimageView)
            val textView1: TextView = itemView.findViewById(R.id.post_username)
            val textView2: TextView = itemView.findViewById(R.id.post_firstname)
            val textView3: TextView = itemView.findViewById(R.id.post_lastname)
            val textView4: TextView = itemView.findViewById(R.id.post_caption)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.ProfileViewHolder{
            val item = LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)
            return ProfileViewHolder(item)
        }
        override fun getItemCount(): Int {
            return profileList.size
        }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val currentItem = profileList[position]
        holder.imageView.setImageResource(currentItem.imageResour)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2
        holder.textView3.text = currentItem.text3
        holder.textView4.text = currentItem.text4
    }
}

