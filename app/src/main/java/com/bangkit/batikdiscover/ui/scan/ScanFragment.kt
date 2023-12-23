package com.bangkit.batikdiscover.ui.scan

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bangkit.batikdiscover.R
import com.bangkit.batikdiscover.data.DataRepository
import com.bangkit.batikdiscover.data.PredictionResponse
import com.bangkit.batikdiscover.databinding.FragmentScanBinding
import com.bangkit.batikdiscover.utils.uriToFile
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

class ScanFragment : Fragment() {

    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!

    private lateinit var galleryLauncher: ActivityResultLauncher<String>
    private lateinit var dataRepository: DataRepository
    private var capturedImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentScanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        galleryLauncher = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            this::handleGalleryResult
        )

        dataRepository = DataRepository(requireContext())

        binding.btnKamera.setOnClickListener {
            val intent = Intent(requireContext(), CameraActivity::class.java)
            startActivity(intent)
        }

        binding.btnGallery.setOnClickListener {
            startGallery()
        }

        // Set OnClickListener for the "Scan Now" button
        binding.btnScanNow.setOnClickListener {
            // Call the logic to handle the scan
            handleScanNow()
        }

        return root
    }

    private fun startGallery() {
        galleryLauncher.launch("image/*")
    }

    private fun handleGalleryResult(uri: Uri?) {
        uri?.let {
            try {
                Log.d("GalleryResult", "Selected image URI: $it")

                // Store the captured image URI for later use if needed
                capturedImageUri = it

                // Load the selected image to ImageView using Glide
                Glide.with(requireContext())
                    .load(it)
                    .into(binding.imageView3)

            } catch (e: Exception) {
                // Log detailed information about the error loading the image
                Log.e("GalleryResult", "Error loading selected image", e)

                // Show a toast with a detailed error message
                showToast("Failed to load selected image: ${e.message}")
            }
        }
    }

    private fun handleScanNow() {
        // Check if an image has been selected
        if (capturedImageUri != null) {
            // Continue with the rest of your logic...
            try {
                val imageFile = uriToFile(capturedImageUri!!, requireContext())


                if (imageFile != null) {
                    // Log detailed information about the compressed file

                    // Use a coroutine to call the suspend function getPredict
                    GlobalScope.launch(Dispatchers.Main) {
                        try {
                            val predictionResponse = dataRepository.getPredict(imageFile)

                            // Log detailed information about the prediction response
                            Log.d("PredictionResponse", "Prediction data: ${predictionResponse.nama}")

                            // Navigate to the detail scan screen
                            navigateToDetailScan(predictionResponse)

                        } catch (e: Exception) {

                            // Show a toast with a detailed error message
                            showToast("Failed to scan: ${e.message}")
                        }
                    }
                } else {
                    // Log detailed information about the compressed file being null
                    showToast("Failed to scan: Compressed file is null")
                }

            } catch (e: Exception) {

                // Show a toast with a detailed error message
                showToast("Failed to scan: ${e.message}")
            }
            // End of your logic...
        } else {
            showToast("Please select an image first.")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToDetailScan(predictionResponse: PredictionResponse) {
        val navController = findNavController()

        val bundle = Bundle().apply {
            putSerializable("predictionData", predictionResponse.nama)
        }

        try {
            navController.navigate(R.id.navigation_scan_detail, bundle)
        } catch (e: Exception) {
            Log.e("ScanFragment", "Error navigating to DetailScanFragment", e)
        }
    }

    fun uriToFile(uri: Uri?): File? {
        if (uri == null) {
            // Handle the null case, perhaps show an error message or log a warning.
            return null
        }
        return File(uri.path)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
