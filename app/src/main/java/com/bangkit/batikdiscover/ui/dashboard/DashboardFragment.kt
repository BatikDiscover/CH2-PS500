package com.bangkit.batikdiscover.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.batikdiscover.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Bind RecyclerViews
        val recyclerViewHotFeature: RecyclerView = binding.recyclerView
        val recyclerViewBatikPilihan: RecyclerView = binding.recyclerArticle
        val recyclerViewEvents: RecyclerView = binding.recyclerEvents

        // Set layout manager for each RecyclerView
        recyclerViewHotFeature.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewBatikPilihan.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewEvents.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

//        // Set adapter for each RecyclerView
//        recyclerViewHotFeature.adapter = HotFeatureAdapter() // Gantilah dengan nama adapter yang sesuai
//        recyclerViewBatikPilihan.adapter = BatikPilihanAdapter() // Gantilah dengan nama adapter yang sesuai
//        recyclerViewEvents.adapter = EventsAdapter() // Gantilah dengan nama adapter yang sesuai

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
