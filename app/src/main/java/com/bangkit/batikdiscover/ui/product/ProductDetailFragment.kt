package com.bangkit.batikdiscover.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bangkit.batikdiscover.R  // Make sure to replace this with the actual R class generated for your project

class ProductDetailFragment : Fragment() {

    private lateinit var imageView8: ImageView
    private lateinit var textView10: TextView
    private lateinit var textView123: TextView
    private lateinit var textView11: TextView
    private lateinit var textView12: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_detail, container, false)

        // Bind views
        imageView8 = view.findViewById(R.id.imageView8)
        textView10 = view.findViewById(R.id.textView10)
        textView123 = view.findViewById(R.id.textVIew123)
        textView11 = view.findViewById(R.id.textView11)
        textView12 = view.findViewById(R.id.textView12)

        // Now you can use these variables to manipulate the views

        return view
    }
}
