package com.bangkit.batikdiscover.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.batikdiscover.R
import com.bangkit.batikdiscover.data.Event
import com.bangkit.batikdiscover.databinding.ItemEventBinding
import com.bumptech.glide.Glide

class EventAdapter(private val eventList: List<Event>) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemEventBinding.bind(itemView)

        fun bind(event: Event) {
            binding.apply {
                judul.text = event.judul
                tanggal.text = event.tanggal
                deskripsi.text = event.deskripsi
                Glide.with(itemView.context)
                    .load(getImageResourceByName(event.gambar))
                    .into(imageView6)
            }
        }

        private fun getImageResourceByName(imageName: String): Int {
            return itemView.context.resources.getIdentifier(
                imageName,
                "drawable",
                itemView.context.packageName
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}
