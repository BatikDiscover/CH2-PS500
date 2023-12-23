// EventFragment.kt
package com.bangkit.batikdiscover.ui.event

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
import com.bangkit.batikdiscover.databinding.FragmentEventBinding
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class EventFragment : Fragment() {

    private var _binding: FragmentEventBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataRepository: DataRepository
    private val eventAdapter = EventAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize dataRepository in onCreateView
        dataRepository = DataRepository(requireContext())

        // Set up RecyclerView adapter with the event data
        binding.recyclerViewEvent.adapter = eventAdapter

        // Set up LinearLayoutManager
        binding.recyclerViewEvent.layoutManager = LinearLayoutManager(requireContext())

        // Fetch events from the API using the user token
        lifecycleScope.launch {
            try {
                val userToken = dataRepository.getUserToken().firstOrNull()
                if (userToken != null) {
                    val eventsResponse = dataRepository.getEvents(userToken)
                    // Assuming the response contains a list of events
                    val events = eventsResponse.eventListData.events
                    eventAdapter.submitList(events)
                } else {
                    // Handle the case where userToken is null
                    // You might want to navigate the user to the login screen or show an error message
                    // For now, let's log a warning
                    Log.w("EventFragment", "User token is null.")
                }
            } catch (e: Exception) {
                // Handle the error more gracefully, e.g., show an error message to the user
                // For now, let's log the error
                e.printStackTrace()
                Log.e("EventFragment", "Error fetching events", e)
            }
        }

        // Set item click listener for navigation
        eventAdapter.onItemClick = { id ->
            val bundle = Bundle().apply {
                putString("id", id)
            }

            try {
                findNavController().navigate(R.id.action_event, bundle)
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
