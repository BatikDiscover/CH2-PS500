package com.bangkit.batikdiscover.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.batikdiscover.databinding.FragmentDashboardBinding
import com.bangkit.batikdiscover.databinding.FragmentProductBinding
import com.bangkit.batikdiscover.ui.dashboard.DashboardViewModel

class ProductFragment : Fragment() {
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val productViewModel =
            ViewModelProvider(this).get(ProductViewModel::class.java)

        _binding = FragmentProductBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textProduct
        productViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    }