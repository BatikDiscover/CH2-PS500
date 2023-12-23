// ProductAdapter.kt

package com.bangkit.batikdiscover.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.batikdiscover.data.BatikItem
import com.bangkit.batikdiscover.databinding.ItemBatikBinding
import com.bumptech.glide.Glide

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(), Filterable {

    private var batiks: List<BatikItem> = emptyList()
    private var filteredBatiks: List<BatikItem> = emptyList()
    var onItemClick: ((String) -> Unit)? = null

    fun submitList(newBatiks: List<BatikItem>) {
        batiks = newBatiks
        filteredBatiks = newBatiks
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(private val binding: ItemBatikBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(batik: BatikItem) {
            binding.apply {
                textView13.text = batik.name

                Glide.with(itemView.context)
                    .load(batik.imageUrl)
                    .into(imageView9)

                itemView.setOnClickListener {
                    onItemClick?.invoke(batik.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemBatikBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val batik = filteredBatiks[position]
        holder.bind(batik)
    }

    override fun getItemCount(): Int = filteredBatiks.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString().toLowerCase()
                val filteredList = if (query.isEmpty()) {
                    batiks
                } else {
                    batiks.filter { it.name.toLowerCase().contains(query) }
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredBatiks = results?.values as List<BatikItem>
                notifyDataSetChanged()
            }
        }
    }
}
