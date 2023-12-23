package com.bangkit.batikdiscover.ui.product

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.batikdiscover.R
import com.bangkit.batikdiscover.data.DataRepository
import com.bangkit.batikdiscover.databinding.FragmentProductBinding
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataRepository: DataRepository
    private val productAdapter = ProductAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dataRepository = DataRepository(requireContext())

        binding.recyclerView.apply {
            adapter = productAdapter
            layoutManager = GridLayoutManager(requireContext(), 2) // Set spanCount to 2 for a 2-column grid
        }

        productAdapter.onItemClick = { id ->
            val bundle = Bundle().apply {
                putString("id", id)
            }

            Log.d("ProductFragment", "Navigating to ProductDetailFragment with id: $id")

            try {
                findNavController().navigate(R.id.action_productFragment_to_productDetailFragment, bundle)
            } catch (e: Exception) {
                Log.e("ProductFragment", "Error navigating to ProductDetailFragment", e)
            }
        }

        lifecycleScope.launch {
            try {
                val userToken = dataRepository.getUserToken().firstOrNull()
                if (userToken != null) {
                    val productsResponse = dataRepository.getBatiks(userToken)
                    val products = productsResponse.batikListData.batiks
                    productAdapter.submitList(products)
                } else {
                    Log.e("ProductFragment", "UserToken is null")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ProductFragment", "Error retrieving batiks", e)
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView = binding.searchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle submission if needed
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle text change (filtering) here
                productAdapter.filter.filter(newText)
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
