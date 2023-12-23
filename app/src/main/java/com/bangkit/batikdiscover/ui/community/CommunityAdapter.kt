package com.bangkit.batikdiscover.ui.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.batikdiscover.data.PostingsItem
import com.bangkit.batikdiscover.data.UserItem
import com.bangkit.batikdiscover.databinding.ItemCommunityBinding
import com.bumptech.glide.Glide

class CommunityAdapter : RecyclerView.Adapter<CommunityAdapter.CommunityViewHolder>() {

    private var items: List<PostingsItem> = emptyList()
    var onItemClick: ((String) -> Unit)? = null

    fun submitList(newItems: List<PostingsItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class CommunityViewHolder(private val binding: ItemCommunityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(posting: PostingsItem) {
            binding.apply {
                ggwp.text = posting.userId
                date.text = posting.date
                // Set the image for imageView6
                Glide.with(itemView.context)
                    .load(posting.imageUrl)
                    .into(imageView6)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        val binding =
            ItemCommunityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        val posting = items[position]
        holder.bind(posting)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(posting.id)
        }
    }

    override fun getItemCount(): Int = items.size
}


