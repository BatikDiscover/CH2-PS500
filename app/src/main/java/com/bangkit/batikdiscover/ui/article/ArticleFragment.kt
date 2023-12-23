package com.bangkit.batikdiscover.ui.article

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.batikdiscover.R
import com.bangkit.batikdiscover.data.DataRepository
import com.bangkit.batikdiscover.databinding.FragmentArticleBinding
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataRepository: DataRepository
    private val articleAdapter = ArticleAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize dataRepository in onCreateView
        dataRepository = DataRepository(requireContext())

        // Set up RecyclerView adapter with the community data
        binding.recyclerViewArticle.adapter = articleAdapter

        // Set up LinearLayoutManager
        binding.recyclerViewArticle.layoutManager = LinearLayoutManager(requireContext())

        // Fetch articles from the API using the user token
        lifecycleScope.launch {
            try {
                val userToken = dataRepository.getUserToken().firstOrNull()
                if (userToken != null) {
                    val articlesResponse = dataRepository.getArticles(userToken)
                    // Assuming the response contains a list of articles
                    val articles = articlesResponse.articleListData.articles
                    articleAdapter.submitList(articles)
                } else {
                    // Handle the case where userToken is null
                    // You might want to navigate the user to the login screen or show an error message
                }
            } catch (e: Exception) {
                // Handle the error, e.g., show a toast or log
                e.printStackTrace()
            }
        }
        articleAdapter.onItemClick = { id ->
            val bundle = Bundle().apply {
                putString("id", id)
            }

            try {
                findNavController().navigate(R.id.action_artikel, bundle)
            } catch (e: Exception) {
                Log.e("EventFragment", "Error navigating to EventDetailFragment", e)
            }
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

