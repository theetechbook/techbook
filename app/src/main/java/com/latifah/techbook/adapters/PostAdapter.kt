package com.latifah.techbook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.latifah.techbook.R
import com.latifah.techbook.database.models.EventsToday
import com.latifah.techbook.database.models.PostData

class PostAdapter(
    private val postList: List<PostData>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        // viewholder represents a single row in our list, one instance of our row
        // it holds one instance of our views that we created our con item layout

        //val imageView: ImageView = itemView.findViewById(R.id.imageView3)
        val textView1: TextView = itemView.findViewById(R.id.post_username)
        val textView2: TextView = itemView.findViewById(R.id.post_firstname)
        val textView3: TextView = itemView.findViewById(R.id.post_lastname)
        val textView4: TextView = itemView.findViewById(R.id.post_caption)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.post, parent, false)
        return PostViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = postList[position]
       // holder.imageView.setImageResource(currentItem.imageResour)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2
        holder.textView3.text = currentItem.text3
        holder.textView4.text = currentItem.text4
    }

}
