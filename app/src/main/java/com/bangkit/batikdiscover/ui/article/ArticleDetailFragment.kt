package com.bangkit.batikdiscover.ui.article

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bangkit.batikdiscover.data.DataRepository
import com.bangkit.batikdiscover.databinding.FragmentDetailArtikelBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ArticleDetailFragment : Fragment() {

    private var _binding: FragmentDetailArtikelBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataRepository: DataRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailArtikelBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dataRepository = DataRepository(requireContext())

        // Retrieve id from arguments
        val id = arguments?.getString("id")

        // Load article details
        lifecycleScope.launch {
            try {
                val userToken = dataRepository.getUserToken().firstOrNull()
                if (userToken != null && id != null) {
                    val articleDetailItem = dataRepository.getArticleById(userToken, id)

                    Log.d("ArticleDetailFragment", "Article Detail Item: $articleDetailItem")

                    // Check if data is not null before setting it to the UI
                    if (articleDetailItem != null) {
                        binding.name.text = articleDetailItem.title
                        binding.date.text = articleDetailItem.author
                        binding.textView11.text = articleDetailItem.date
                        binding.textView12.text = articleDetailItem.content
                        binding.location.text = articleDetailItem.source


                            Glide.with(requireContext())
                                .load(binding.imageView8)

                    } else {
                        Log.e("ArticleDetailFragment", "Article Detail Item is null")
                    }
                } else {
                    // Handle the case where userToken or id is null
                    // You might want to navigate the user to the login screen or show an error message
                    Log.e("ArticleDetailFragment", "UserToken or id is null")
                }
            } catch (e: Exception) {
                // Handle the error, e.g., show a toast or log
                e.printStackTrace()
                Log.e("ArticleDetailFragment", "Error retrieving article details", e)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
