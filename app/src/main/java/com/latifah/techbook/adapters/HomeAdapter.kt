package com.latifah.techbook.adaptersimport
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import com.latifah.techbook.R
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.latifah.techbook.database.models.HomeData
import com.latifah.techbook.database.models.Post
import com.latifah.techbook.ui.viewmodels.TechbookViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.extensions.LayoutContainer

class HomeAdapter(private val exampleList: MutableList<Post?>, private val listener: OnItemClickListener, private val context: Context) : RecyclerView.Adapter<HomeAdapter.Viewholder>() {
    // var data: List<HomeData> = emptyList()
    //   set(newList) {
    //      val calculateDiff = DiffUtil.calculateDiff(DiffCallback(field, newList))
    //     calculateDiff.dispatchUpdatesTo(this)
    //     field = newList
    //     }



    override fun getItemCount(): Int = exampleList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_item,parent,false)
        return Viewholder(itemView)
    }

    override fun onBindViewHolder(holder: HomeAdapter.Viewholder, position: Int) {
        val currentItem = exampleList[position]
        //holder.textView1.text = currentItem.text1
        //holder.textView2.text = currentItem.text2
        //holder.textView3.text = currentItem.text3
        Log.d("using Glide", "${currentItem?.gif_url}")
        Glide.with(context).load(currentItem?.gif_url).into(holder.post_image)
    }

    inner class Viewholder( val itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        /* val imageView: ImageView = itemView.findViewById(R.id.logo)
         val imageView3: ImageView = itemView.findViewById(R.id.heart)
         val imageView4: ImageView = itemView.findViewById(R.id.send)
         val imageView5: ImageView = itemView.findViewById(R.id.reply)
         val imageView6: ImageView = itemView.findViewById(R.id.bookmark)
         */
        val textView1: TextView = itemView.findViewById(R.id.brand_name)
        val textView2: TextView = itemView.findViewById(R.id.likes_txt)
        val textView3: TextView = itemView.findViewById(R.id.description_txt)
        val post_image: ImageView = itemView.findViewById(R.id.post_img)

        //val editText: EditText = itemView.findViewById(R.id.likes_txt)



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


    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}

/*class DiffCallback(val oldList: List<HomeData>, val newList: List<HomeData>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]
        return old.id == new.id
    }
    override fun getOldListSize(): Int {
        return oldList.size
    }
    override fun getNewListSize(): Int {
        return newList.size
    }
 */
