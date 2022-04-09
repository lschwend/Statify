package com.example.statify.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.statify.R
import com.example.statify.data.adapter.HomeAdapter.UserViewHolder
import com.example.statify.data.model.Tracks

class HomeAdapter : ListAdapter<Tracks, UserViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val userView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            userView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): UserViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
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