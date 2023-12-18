package com.bangkit.batikdiscover.ui.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bangkit.batikdiscover.R

class DetailScanFragment : Fragment() {

    private lateinit var imageView4: ImageView
    private lateinit var textView7: TextView
    private lateinit var imageView7: ImageView
    private lateinit var textView9: TextView
    private lateinit var textView98: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_result_scan, container, false)

        // Bind views
        imageView4 = view.findViewById(R.id.imageView4)
        textView7 = view.findViewById(R.id.textView7)
        imageView7 = view.findViewById(R.id.imageView7)
        textView9 = view.findViewById(R.id.textView9)
        textView98 = view.findViewById(R.id.textView98)

        // Now you can use these variables to manipulate the views

        return view
    }
}