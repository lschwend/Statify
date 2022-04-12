package com.example.statify.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.statify.R
import com.example.statify.data.adapter.TrackAdapter.UserViewHolder
import com.example.statify.data.model.Artists
import com.example.statify.data.model.Tracks

class TrackAdapter : ListAdapter<Tracks, UserViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name, current.artists)
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val userView: TextView = itemView.findViewById(R.id.track)
        private val artisView: TextView = itemView.findViewById(R.id.artist)

        fun bind(text: String?, artists: List<Artists>?) {
            userView.text = text
            var artistText = ""
            if (artists != null) {
                for(artist in artists) {
                    artistText += (artist.name + ", \n")
                }
            }
            artisView.text = artistText
        }

        companion object {
            fun create(parent: ViewGroup): UserViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.trackview_item, parent, false)
                return UserViewHolder(view)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Tracks>() {
            override fun areItemsTheSame(oldItem: Tracks, newItem: Tracks): Boolean {
                return oldItem.name === newItem.name
            }

            override fun areContentsTheSame(oldItem: Tracks, newItem: Tracks): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

}