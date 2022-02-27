package com.latifah.techbook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.latifah.techbook.R
import com.latifah.techbook.adapters.PostAdapter.PostViewHolder
import com.latifah.techbook.database.models.ContactItem
import com.latifah.techbook.database.models.PostData

class PostAdapter ( private val  postList: ArrayList<PostData>,
                  private val itemClickListener: OnItemClickListener
                   ) : RecyclerView.Adapter<PostViewHolder>() {


    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        // viewholder represents a single row in our list, one instance of our row
        // it holds one instance of our views that we created our con item layout
        val imageView: ImageView = itemView.findViewById(R.id.postimageView)
        val textView1: TextView = itemView.findViewById(R.id.post_username)
        val textView2: TextView = itemView.findViewById(R.id.post_firstname)
        val textView3: TextView = itemView.findViewById(R.id.post_lastname)
        val textView4: TextView = itemView.findViewById(R.id.post_caption)

        init {
            itemView.setOnClickListener (this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            itemClickListener.onItemClick(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent,false)

        return PostViewHolder(view)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = postList[position]
        holder.imageView.setImageResource(currentItem.imageResour)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2
        holder.textView3.text = currentItem.text3
        holder.textView4.text = currentItem.text4
    }
    override fun getItemCount(): Int {
        return postList.size
    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}
