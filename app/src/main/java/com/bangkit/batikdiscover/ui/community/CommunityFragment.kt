// CommunityFragment.kt
package com.bangkit.batikdiscover.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.batikdiscover.data.Community
import com.bangkit.batikdiscover.databinding.FragmentComunityBinding

class CommunityFragment : Fragment() {

    private var _binding: FragmentComunityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentComunityBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Your list of community event data
        val communityList = getDummyCommunityList()

        // Set up RecyclerView adapter with the community data
        val adapter = CommunityAdapter(communityList)
        binding.recyclerViewCommunity.adapter = adapter

        // Set up LinearLayoutManager
        binding.recyclerViewCommunity.layoutManager = LinearLayoutManager(requireContext())

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Function to provide dummy community event data
    private fun getDummyCommunityList(): List<Community> {
        // Use the community event data from your JSON here
        // For now, using placeholder values
        return listOf(
            Community("Quiz Bahasa Indonesia", "13-02-2031", "Romi Jatmiko", "events"),
            Community("Coding Workshop", "20-02-2031", "Dian Pratama", "workshop"),
            // Add more community events as needed
        )
    }
}
