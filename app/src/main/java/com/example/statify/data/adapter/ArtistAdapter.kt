package com.example.statify.data.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.statify.R
import com.example.statify.data.model.Artists
import com.example.statify.data.adapter.ArtistAdapter.UserViewHolder
import com.squareup.picasso.Picasso

class ArtistAdapter : ListAdapter<Artists, UserViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = getItem(position)
        val imageUrl = current.images[0].url.toUri()
        holder.bind(current.name, imageUrl)
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val userView: TextView = itemView.findViewById(R.id.track)
        private val imageView: ImageView = itemView.findViewById(R.id.image)

        fun bind(text: String?, image: Uri) {
            userView.text = text
            Picasso.get().load(image).into(imageView)
        }

        companion object {
            fun create(parent: ViewGroup): UserViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.artistkview_item, parent, false)
                return UserViewHolder(view)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Artists>() {
            override fun areItemsTheSame(oldItem: Artists, newItem: Artists): Boolean {
                return oldItem.name === newItem.name
            }

            override fun areContentsTheSame(oldItem: Artists, newItem: Artists): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

}