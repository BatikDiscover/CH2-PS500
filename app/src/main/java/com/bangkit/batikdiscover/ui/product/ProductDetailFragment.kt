// ProductDetailFragment.kt

package com.bangkit.batikdiscover.ui.product

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bangkit.batikdiscover.data.DataRepository
import com.bangkit.batikdiscover.databinding.FragmentProductDetailBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataRepository: DataRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dataRepository = DataRepository(requireContext())

        // Retrieve id from arguments
        val id = arguments?.getString("id")

        // Load product details
        lifecycleScope.launch {
            try {
                val userToken = dataRepository.getUserToken().firstOrNull()
                if (userToken != null && id != null) {
                    val batikDetailItem = dataRepository.getBatikById(userToken, id)

                    binding.textView10.text = batikDetailItem?.name
                    binding.textView5.text = batikDetailItem?.origin
                    binding.textView12.text = batikDetailItem?.meaning
                    binding.textView18.text = batikDetailItem?.pattern

                    Glide.with(requireContext())
                        .load(batikDetailItem?.imageUrl)
                        .into(binding.imageView8)

                } else {
                    // Handle the case where userToken or id is null
                    // You might want to navigate the user to the login screen or show an error message
                    Log.e("ProductDetailFragment", "UserToken or id is null")
                }
            } catch (e: Exception) {
                // Handle the error, e.g., show a toast or log
                e.printStackTrace()
                Log.e("ProductDetailFragment", "Error retrieving batik details", e)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}