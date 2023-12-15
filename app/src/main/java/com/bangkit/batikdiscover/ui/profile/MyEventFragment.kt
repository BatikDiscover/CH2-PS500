package com.bangkit.batikdiscover.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bangkit.batikdiscover.databinding.FragmentEventBinding

class MyEventsFragment : Fragment() {
    private var _binding: FragmentEventBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // TODO: Implement your logic for MyEventsFragment

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}