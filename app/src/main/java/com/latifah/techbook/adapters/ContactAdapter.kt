package com.latifah.techbook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.latifah.techbook.R
import com.latifah.techbook.database.models.ContactItem
import com.latifah.techbook.database.models.PostData

class ContactAdapter(
                     private val listener: (ContactItem) -> Unit) :
   ListAdapter<ContactItem, ContactAdapter.ContactViewHolder>(ContactDiffCallback) {


    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        // viewholder represents a single row in our list, one instance of our row
        // it holds one instance of our views that we created our con item layout
       private val imageContact: ImageView = itemView.findViewById(R.id.imagV)
       private val userName: TextView = itemView.findViewById(R.id.con_txt)
       private val profileName: TextView = itemView.findViewById(R.id.con_subtxt)
        private var currentUser: ContactItem? = null




        init {
            itemView.setOnClickListener(this)
        }

        fun bind(contactItem: ContactItem,listener: (ContactItem) -> Unit){
            currentUser = contactItem

            userName.text = contactItem.text1
            profileName.text = contactItem.text2
            if (contactItem.imageResour != null){
                imageContact.setImageResource(contactItem.imageResour)
            }else{
                imageContact.setImageResource(R.drawable.techiehumor)
            }
        }
       override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if(position!= RecyclerView.NO_POSITION){
               // currentUser.onItemClick(position)
            }
        }
    }


    /* Creates and inflates view and return ViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)
        return ContactViewHolder(itemView)
    }

    /* Gets current currentItem and uses it to bind view. */
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem, listener)
      //  holder.imageView.setImageResource(currentItem.imageResour)
        //holder.textView1.text = currentItem.text1
        //holder.textView2.text = currentItem.text2
    }


    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}
object ContactDiffCallback : DiffUtil.ItemCallback<ContactItem>() {
    override fun areItemsTheSame(oldItem: ContactItem, newItem: ContactItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ContactItem, newItem: ContactItem): Boolean {
        return oldItem.id == newItem.id
    }
}