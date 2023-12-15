package com.bangkit.batikdiscover.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.batikdiscover.databinding.FragmentProfileBinding

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

        // TODO: Setup UI components and observe ViewModel data

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
