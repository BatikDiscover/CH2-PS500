package com.bangkit.batikdiscover.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.batikdiscover.R
import com.bangkit.batikdiscover.data.Batik
import com.bangkit.batikdiscover.databinding.ItemBatikBinding
import com.bumptech.glide.Glide

class ProductAdapter(private val batikList: List<Batik>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemBatikBinding.bind(itemView)

        fun bind(batik: Batik) {
            binding.apply {
                // Set the values from the Batik object to the views in the layout
                textView13.text = batik.judul
                Glide.with(itemView.context)
                    .load(getImageResourceByName(batik.imageFileName))
                    .into(imageView9)

                // Set the image for imageView9
                imageView9.setImageResource(getImageResourceByName(batik.imageFileName))
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_batik, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val batik = batikList[position]
        holder.bind(batik)
    }

    override fun getItemCount(): Int {
        return batikList.size
    }
}
