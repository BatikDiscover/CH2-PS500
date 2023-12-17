// ScanFragment.kt

package com.bangkit.batikdiscover.ui.scan

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bangkit.batikdiscover.databinding.FragmentScanBinding

class ScanFragment : Fragment() {

    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!

    private lateinit var galleryLauncher: ActivityResultLauncher<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize gallery launcher
        galleryLauncher = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            this::handleGalleryResult
        )

        // Set up your UI and handle button clicks here

        binding.btnKamera.setOnClickListener {
            val intent = Intent(requireContext(), CameraActivity::class.java)
            startActivity(intent)
        }

        binding.btnGallery.setOnClickListener {
            // Launch gallery when gallery button is clicked
            startGallery()
        }

        return root
    }

    private fun startGallery() {
        galleryLauncher.launch("image/*")
    }

    private fun handleGalleryResult(uri: Uri?) {
        // Handle the selected image URI here
        // uri will be null if the user cancels the gallery selection
        uri?.let {
            // Do something with the selected image URI
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
