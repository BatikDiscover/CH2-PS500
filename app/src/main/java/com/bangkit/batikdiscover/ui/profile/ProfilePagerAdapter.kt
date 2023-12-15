package com.bangkit.batikdiscover.ui.profile

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProfilePagerAdapter(fragmentActivity: ProfileFragment) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2 // Number of tabs

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyFavoriteFragment() // Replace with your actual fragment class
            1 -> MyEventsFragment() // Replace with your actual fragment class
            // Add more fragments if needed
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}