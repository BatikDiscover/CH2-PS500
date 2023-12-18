package com.bangkit.batikdiscover.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.batikdiscover.R
import com.bangkit.batikdiscover.data.Batik
import com.bangkit.batikdiscover.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // ViewModelProvider is used to create a ViewModel instance associated with the fragment
        val dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Create a list of Batik objects from the provided JSON data
        val batikList = listOf(
            Batik("Batik Parang Barong", "Motif Batik Parang Barong menggambarkan seekor barong, simbol dari keberanian dan kekuatan. Batik ini sering digunakan pada acara-acara adat dan memiliki warna-warna yang mencolok seperti merah, hitam, dan putih.", "batik_bedjo"),
            Batik("Batik Tambal", "Batik Tambal memiliki ciri khas dengan motif yang menyerupai potongan kain yang disatukan kembali. Motif ini melambangkan kehidupan yang penuh liku-liku dan keunikan. Warna-warna yang umum digunakan adalah biru, coklat, dan hijau.", "batik_bedjo"),
            Batik("Batik Parang Barong", "Motif Batik Parang Barong menggambarkan seekor barong, simbol dari keberanian dan kekuatan. Batik ini sering digunakan pada acara-acara adat dan memiliki warna-warna yang mencolok seperti merah, hitam, dan putih.", "batik_bedjo"),
            Batik("Batik Tambal", "Batik Tambal memiliki ciri khas dengan motif yang menyerupai potongan kain yang disatukan kembali. Motif ini melambangkan kehidupan yang penuh liku-liku dan keunikan. Warna-warna yang umum digunakan adalah biru, coklat, dan hijau.", "batik_bedjo"),

            )

        // Create a ProductAdapter and pass the list of Batik objects
        val productAdapter = ProductAdapter(batikList)

        // Set the adapter for recyclerViewHotFeature
        binding.recyclerView.adapter = productAdapter
        // Set layout manager for recyclerViewHotFeature
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // Set click listeners for navigation
        binding.iconArticle.setOnClickListener {
            // Navigasi ke halaman ArticleFragment
            findNavController().navigate(R.id.navigation_artikel)
        }

        binding.iconEvents.setOnClickListener {
            // Navigasi ke halaman EventFragment
            findNavController().navigate(R.id.navigtaion_events)
        }

        binding.scan.setOnClickListener {
            // Navigasi ke halaman ScanFragment
            findNavController().navigate(R.id.navigation_scan)
        }

        binding.community.setOnClickListener {
            // Navigasi ke halaman CommunityFragment
            findNavController().navigate(R.id.navigation_community)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
