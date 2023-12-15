package com.bangkit.batikdiscover.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.batikdiscover.databinding.FragmentProfileBinding
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    // ViewModel for ProfileFragment
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize ViewModel
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        // Setup ViewPager2 with tabs
        val viewPager = binding.viewPager
        val tabLayout = binding.tabs

        viewPager.adapter = ProfilePagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "My Favorite Batik"
                1 -> tab.text = "My Events"
                // Add more tabs if needed
            }
        }.attach()

        // TODO: Setup UI components and observe ViewModel data

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}