// DetailEventFragment.kt
package com.bangkit.batikdiscover.ui.event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bangkit.batikdiscover.data.DataRepository
import com.bangkit.batikdiscover.databinding.FragmentDetailEventBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class DetailEventFragment : Fragment() {

    private var _binding: FragmentDetailEventBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataRepository: DataRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailEventBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dataRepository = DataRepository(requireContext())

        // Retrieve id from arguments
        val id = arguments?.getString("id")

        // Load event details
        lifecycleScope.launch {
            try {
                val userToken = dataRepository.getUserToken().firstOrNull()
                if (userToken != null && id != null) {
                    val eventDetailItem = dataRepository.getEventById(userToken, id)

                    binding.name.text = eventDetailItem?.name
                    binding.date.text = "Date: ${eventDetailItem?.date}"
                    binding.location.text = "Location: ${eventDetailItem?.location}"
                    binding.textView12.text = eventDetailItem?.description

                     Glide.with(requireContext())
                         .load(eventDetailItem?.imageEventItem)
                         .into(binding.imageView8)

                } else {
                    // Handle the case where userToken or id is null
                    // You might want to navigate the user to the login screen or show an error message
                    Log.e("DetailEventFragment", "UserToken or id is null")
                }
            } catch (e: Exception) {
                // Handle the error, e.g., show a toast or log
                e.printStackTrace()
                Log.e("DetailEventFragment", "Error retrieving event details", e)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
