package com.bangkit.batikdiscover.ui.community

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.batikdiscover.data.CommunityDetailItem
import com.bangkit.batikdiscover.data.CommentItem
import com.bangkit.batikdiscover.data.DataRepository
import com.bangkit.batikdiscover.databinding.FragmentCommunityDetailBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class CommunityDetailFragment : Fragment() {

    private var _binding: FragmentCommunityDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataRepository: DataRepository
    private lateinit var commentAdapter: CommentAdapter
    private var communityId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommunityDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dataRepository = DataRepository(requireContext())

        // Retrieve id from arguments
        val id = arguments?.getString("id")

        // Load community details
        lifecycleScope.launch {
            try {
                val userToken = dataRepository.getUserToken().firstOrNull()
                if (userToken != null && id != null) {
                    val communityDetailItem = dataRepository.getCommunityById(userToken, id)

                    Log.d("CommunityDetailFragment", "Community Detail Item: $communityDetailItem")

                    // Check if data is not null before setting it to the UI
                    if (communityDetailItem != null) {
                        displayCommunityDetails(communityDetailItem)
                        communityId = id
                        setupCommentRecyclerView()
                        loadComments(userToken, id)
                    } else {
                        handleCommunityDetailNull()
                    }
                } else {
                    handleNullParameters()
                }
            } catch (e: Exception) {
                handleCommunityDetailsError(e)
            }
        }

        // Set up comment posting
        binding.textView16.setOnClickListener {
            handleCommentPosting()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayCommunityDetails(communityDetailItem: CommunityDetailItem) {
        binding.judul.text = communityDetailItem.userId
        binding.judul2.text = communityDetailItem.date
        binding.judul3.text = communityDetailItem.title
        binding.desc.text = communityDetailItem.content

        val imageUrl = communityDetailItem.imageUrl

        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(requireContext())
                .load(imageUrl)
                .into(binding.imageView6)
        } else {
            // Handle the case where the imageUrl is null or empty
            // You might want to set a placeholder image or hide the ImageView
            // For example, you can use: binding.imageView6.visibility = View.GONE
        }
    }

    private fun handleNullParameters() {
        // Handle the case where userToken or id is null
        // You might want to navigate the user to the login screen or show an error message
        Log.e("CommunityDetailFragment", "UserToken or id is null")
    }

    private fun handleCommunityDetailNull() {
        // Handle the case where communityDetailItem is null
        // You might want to show an error message or handle it accordingly
        Log.e("CommunityDetailFragment", "Community Detail Item is null")
    }

    private fun handleCommunityDetailsError(e: Exception) {
        // Handle the error, e.g., show a toast or log
        e.printStackTrace()
        Log.e("CommunityDetailFragment", "Error retrieving community details", e)
    }

    private fun setupCommentRecyclerView() {
        commentAdapter = CommentAdapter()
        binding.recyclerView2.adapter = commentAdapter
        binding.recyclerView2.layoutManager = LinearLayoutManager(requireContext())
    }


    private fun loadComments(userToken: String, communityId: String) {
        lifecycleScope.launch {
            try {
                val comments = dataRepository.getCommentsByPostId(userToken, communityId)
                commentAdapter.submitList(comments)
            } catch (e: Exception) {
                // Handle the error, e.g., show a toast or log
                e.printStackTrace()
                Log.e("CommunityDetailFragment", "Error retrieving comments", e)
            }
        }
    }

    private fun handleCommentPosting() {
        lifecycleScope.launch {
            try {
                val userToken = dataRepository.getUserToken().firstOrNull()
                val commentContent = binding.textInput.text.toString()
                if (commentContent.isNotBlank()) {
                    if (userToken != null) {
                        postComment(userToken, communityId.orEmpty(), commentContent)
                    }
                } else {
                    // Handle empty comment content, e.g., show a toast or log
                    Log.e("CommunityDetailFragment", "Comment content is empty")
                }
            } catch (e: Exception) {
                // Handle the error, e.g., show a toast or log
                e.printStackTrace()
                Log.e("CommunityDetailFragment", "Error posting comment", e)
            }
        }
    }

    private fun postComment(userToken: String, communityId: String, commentRequest: String) {
        Log.d("CommunityDetailFragment", "User Token: $userToken") // Log the user token

        lifecycleScope.launch {
            try {
                // Implement the method to post a comment
                dataRepository.addCommentToPost(userToken, communityId, commentRequest)

                // After posting the comment, reload the comments
                loadComments(userToken, communityId)
            } catch (e: Exception) {
                // Handle the error, e.g., show a toast or log
                e.printStackTrace()
                Log.e("CommunityDetailFragment", "Error posting comment", e)
            }
        }
    }

}

