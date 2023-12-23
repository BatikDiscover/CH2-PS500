// EventAdapter.kt
package com.bangkit.batikdiscover.ui.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.batikdiscover.R
import com.bangkit.batikdiscover.data.EventItem
import com.bangkit.batikdiscover.databinding.ItemEventBinding
import com.bumptech.glide.Glide

class EventAdapter :
    ListAdapter<EventItem, EventAdapter.EventViewHolder>(EventDiffCallback()) {

    // Define the onItemClick lambda
    var onItemClick: ((String) -> Unit)? = null

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemEventBinding.bind(itemView)

        fun bind(event: EventItem) {
            binding.apply {
                judul.text = event.name
                tanggal.text = event.date
                deskripsi.text = event.description
                location.text = event.location

                // Set the image for imageView6 using Glide
                Glide.with(itemView.context)
                    .load(event.imageEventItem)
                    .into(imageView6)

                itemView.setOnClickListener {
                    // Invoke the onItemClick lambda with the event ID
                    onItemClick?.invoke(event.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

    class EventDiffCallback : DiffUtil.ItemCallback<EventItem>() {
        override fun areItemsTheSame(oldItem: EventItem, newItem: EventItem): Boolean {
            return oldItem.date == newItem.date // Assuming date is unique
        }

        override fun areContentsTheSame(oldItem: EventItem, newItem: EventItem): Boolean {
            return oldItem == newItem
        }
    }
}
