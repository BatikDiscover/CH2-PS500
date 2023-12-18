// CommunityAdapter.kt
package com.bangkit.batikdiscover.ui.community

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.batikdiscover.R
import com.bangkit.batikdiscover.data.Community
import com.bangkit.batikdiscover.databinding.ItemCommunityBinding
import com.bumptech.glide.Glide

class CommunityAdapter(private val communityList: List<Community>) :
    RecyclerView.Adapter<CommunityAdapter.CommunityViewHolder>() {

    inner class CommunityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCommunityBinding.bind(itemView)

        fun bind(event: Community) {
            binding.apply {
                judul2.text = event.judul
                kaka.text = event.tanggal
                judul.text = event.nama

                // Set the image for imageViewEvent
                Glide.with(itemView.context)
                    .load(getImageResourceByName(event.image))
                    .into(imageView6)
            }
        }

        // Function to get the resource ID of the image by its name
        private fun getImageResourceByName(imageName: String): Int {
            return itemView.context.resources.getIdentifier(
                imageName,
                "drawable",
                itemView.context.packageName
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_community, parent, false)
        return CommunityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        val event = communityList[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int {
        return communityList.size
    }
}
