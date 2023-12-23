package com.bangkit.batikdiscover.ui.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.batikdiscover.data.CommentItem
import com.bangkit.batikdiscover.databinding.ItemKomentarBinding

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private var comments: List<CommentItem> = emptyList()

    fun submitList(newComments: List<CommentItem>) {
        comments = newComments
        notifyDataSetChanged()
    }

    inner class CommentViewHolder(private val binding: ItemKomentarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: CommentItem) {
            binding.apply {
                // Set data to views
                nama.text = comment.userId
                isiKomentar.text = comment.content
                tanggalNotif2.text = comment.date

                // You can load profile image here if available
                // Glide.with(itemView.context)
                //     .load(comment.profileImageUrl)
                //     .into(iconEvents)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemKomentarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]
        holder.bind(comment)
    }

    override fun getItemCount(): Int = comments.size
}