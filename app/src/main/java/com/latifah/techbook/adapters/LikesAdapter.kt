package com.latifah.techbook.adaptersimport

import android.content.Context
import android.view.LayoutInflater
import com.latifah.techbook.R
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.latifah.techbook.adapters.TechEventAdapter
import com.latifah.techbook.database.models.LikesData
import kotlinx.android.extensions.LayoutContainer

class LikesAdapter(private val likesList: List<LikesData>, private val listener:OnItemClickListener, private val context: Context?) : RecyclerView.Adapter<LikesAdapter.LikesViewholder>() {


    override fun getItemCount(): Int {
        return likesList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikesViewholder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.likes_item, parent, false)
        return LikesViewholder(itemView)
    }

    override fun onBindViewHolder(holder: LikesAdapter.LikesViewholder, position: Int) {
        if(position < likesList.size) {
            val currentItem = likesList[position]
            holder.otherUserName.text = currentItem.text1
            holder.userInfo.text = currentItem.text2
            if (context != null) {
                Glide.with(context).load(currentItem?.imageResour).into(holder.likesImage)
            }

        }
    }

     class LikesViewholder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var likesImage: ImageView = itemView.findViewById(R.id.like_img)
        var otherUserName: TextView = itemView.findViewById(R.id.likes_txt)
       var userInfo: TextView = itemView.findViewById(R.id.likes_subtxt)
        }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }


}
