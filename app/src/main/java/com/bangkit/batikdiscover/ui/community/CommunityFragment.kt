package com.bangkit.batikdiscover.ui.community

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
import com.bangkit.batikdiscover.data.CommunityResponse
import com.bangkit.batikdiscover.data.DataRepository
import com.bangkit.batikdiscover.data.PostingsItem
import com.bangkit.batikdiscover.databinding.FragmentComunityBinding
import com.bangkit.batikdiscover.ui.community.CommunityAdapter
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class CommunityFragment : Fragment() {

    private var _binding: FragmentComunityBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataRepository: DataRepository
    private val communityAdapter = CommunityAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentComunityBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize dataRepository in onCreateView
        dataRepository = DataRepository(requireContext())

        // Set up RecyclerView adapter with the community data
        binding.recyclerViewCommunity.adapter = communityAdapter

        // Set up LinearLayoutManager
        binding.recyclerViewCommunity.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            try {
                val userToken = dataRepository.getUserToken().firstOrNull()
                if (userToken != null) {
                    // Fetch community posts
                    val communityResponse = dataRepository.getCommunityPosts(userToken)
                    val postings = communityResponse.postData.postings

                    // Log retrieved posts
                    Log.d("CommunityFragment", "Retrieved ${postings.size} community posts")

                    // Fetch user details by ID
                    val user = dataRepository.getUserById(userToken)

                    if (user != null) {
                        // Log retrieved user details
                        Log.d("CommunityFragment", "Retrieved user details: $user")

                        // Combine community posts and user details
                        val communityItems = mutableListOf<PostingsItem>()
                        communityItems.addAll(postings)

                        if (communityItems.isNotEmpty()) {
                            // Submit the list to the adapter
                            communityAdapter.submitList(communityItems)
                        } else {
                            // Log empty community posts
                            Log.d("CommunityFragment", "No community posts found")
                            // Handle the case where there are no community posts
                        }
                    } else {
                        // Log failure to retrieve user details
                        Log.e("CommunityFragment", "Failed to retrieve user details")
                        // Handle the case where user details couldn't be fetched
                    }
                } else {
                    // Log null user token
                    Log.e("CommunityFragment", "User token is null")
                    // Handle the case where userToken is null
                    // You might want to navigate the user to the login screen or show an error message
                }
            } catch (e: Exception) {
                // Log the exception
                Log.e("CommunityFragment", "Error fetching community posts: ${e.message}")
                // Handle the error, e.g., show a toast or log
                e.printStackTrace()
            }
        }

        communityAdapter.onItemClick = { id ->
            val bundle = Bundle().apply {
                putString("id", id)
            }

            try {
                findNavController().navigate(R.id.action_community, bundle)
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


