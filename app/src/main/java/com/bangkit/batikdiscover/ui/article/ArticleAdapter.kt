package com.bangkit.batikdiscover.ui.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.batikdiscover.data.ArticleItem
import com.bangkit.batikdiscover.databinding.ItemArticleBinding
import com.bumptech.glide.Glide

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    private var articles: List<ArticleItem> = emptyList()
    var onItemClick: ((String) -> Unit)? = null

    fun submitList(newArticles: List<ArticleItem>) {
        articles = newArticles
        notifyDataSetChanged()
    }

    inner class ArticleViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: ArticleItem) {
            binding.apply {
                title.text = article.title
                author.text = article.author
                date2.text = article.date
                content.text = article.content


                itemView.setOnClickListener {
                    // Handle click event if needed
                    onItemClick?.invoke(article.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int = articles.size
}
