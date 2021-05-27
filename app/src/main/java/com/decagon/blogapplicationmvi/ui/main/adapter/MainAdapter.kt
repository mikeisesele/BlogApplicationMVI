package com.decagon.blogapplicationmvi.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.blogapplicationmvi.R
import com.decagon.blogapplicationmvi.data.model.Post

class MainAdapter(private val posts: ArrayList<Post>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : DataViewHolder {
        val layout = (LayoutInflater.from(parent.context).inflate(R.layout.post_model, parent, false))
        return DataViewHolder(layout)
    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
//        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false))

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) = with (itemView) {
            val title: TextView = itemView.findViewById(R.id.title)
            val body: TextView = itemView.findViewById(R.id.post)
            var card: CardView = itemView.findViewById(R.id.materialCardView)

            title.text = post.title
            body.text = post.body
        }
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int){ holder.bind(posts[position]) }

    fun addData(list: List<Post>) { posts.addAll(list) }
}