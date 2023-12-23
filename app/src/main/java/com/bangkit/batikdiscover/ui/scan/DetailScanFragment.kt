package com.bangkit.batikdiscover.ui.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bangkit.batikdiscover.R
import com.bangkit.batikdiscover.data.BatikDetailItem
import com.bangkit.batikdiscover.data.DataRepository
import com.bangkit.batikdiscover.databinding.FragmentResultScanBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class DetailScanFragment : Fragment() {

    private var _binding: FragmentResultScanBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataRepository: DataRepository

    private var userId: String? = null  // Variable to store the id

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataRepository = DataRepository(requireContext())

        // Mengambil data dari arguments
        val predictionData = arguments?.getSerializable("predictionData") as? String

        // Menampilkan data pada UI
        predictionData?.let {
            binding.textView9.text = it

            // Set the id for later use
            userId = it

            // Fetch batik information by name
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    // Retrieve the user token
                    val userToken = dataRepository.getUserToken().firstOrNull()

                    // Check if the user token is not null
                    if (!userToken.isNullOrEmpty()) {
                        // Fetch batik details by ID using your repository function
                        val batikDetail = dataRepository.getBatikById(userToken, userId!!)

                        // Update UI with batik details
                        updateUIWithBatikData(batikDetail)
                    } else {
                        showToast("User token is not available.")
                    }
                } catch (e: Exception) {
                    // Handle error fetching batik data
                    showToast("Failed to fetch batik data: ${e.message}")
                }
            }
        }

        // Tambahkan listener untuk tombol "Lihat Detail" jika diperlukan
        binding.textView98.setOnClickListener {
            // Tambahkan logika untuk menangani klik tombol

            // Navigate to the ProductDetailFragment using the stored id
            userId?.let { id ->
                val bundle = Bundle().apply {
                    putString("id", id)
                }
                findNavController().navigate(R.id.action_hasil_scan, bundle)
            } ?: showToast("User id is not available.")
        }
    }

    private fun updateUIWithBatikData(batikDetail: BatikDetailItem?) {
        // Update UI with batik information (e.g., display image, description, etc.)
        if (batikDetail != null) {
            Glide.with(this).load(batikDetail.imageUrl).into(binding.imageView7)
            Glide.with(this).load(batikDetail.imageUrl).into(binding.imageView4)
            binding.textView9.text = batikDetail.name
        } else {
            showToast("Batik details not available.")
        }
    }

    private fun showToast(message: String) {
        // Show a toast with the provided message
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
