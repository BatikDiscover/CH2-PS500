package com.bangkit.batikdiscover.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.batikdiscover.data.Batik
import com.bangkit.batikdiscover.databinding.FragmentProductBinding
import com.bangkit.batikdiscover.ui.dashboard.ProductAdapter

class ProductFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProductBinding.inflate(inflater, container, false)

        // Your list of dummy Batik data
        val batikList = listOf(
            Batik("Batik Parang Barong", "Motif Batik Parang Barong menggambarkan seekor barong, simbol dari keberanian dan kekuatan. Batik ini sering digunakan pada acara-acara adat dan memiliki warna-warna yang mencolok seperti merah, hitam, dan putih.", "batik_bedjo"),
            Batik("Batik Tambal", "Batik Tambal memiliki ciri khas dengan motif yang menyerupai potongan kain yang disatukan kembali. Motif ini melambangkan kehidupan yang penuh liku-liku dan keunikan. Warna-warna yang umum digunakan adalah biru, coklat, dan hijau.", "batik_bedjo"),
            Batik("Batik Parang Barong", "Motif Batik Parang Barong menggambarkan seekor barong, simbol dari keberanian dan kekuatan. Batik ini sering digunakan pada acara-acara adat dan memiliki warna-warna yang mencolok seperti merah, hitam, dan putih.", "batik_bedjo"),
            Batik("Batik Tambal", "Batik Tambal memiliki ciri khas dengan motif yang menyerupai potongan kain yang disatukan kembali. Motif ini melambangkan kehidupan yang penuh liku-liku dan keunikan. Warna-warna yang umum digunakan adalah biru, coklat, dan hijau.", "batik_bedjo") ,
                    Batik("Batik Parang Barong", "Motif Batik Parang Barong menggambarkan seekor barong, simbol dari keberanian dan kekuatan. Batik ini sering digunakan pada acara-acara adat dan memiliki warna-warna yang mencolok seperti merah, hitam, dan putih.", "batik_bedjo"),
        Batik("Batik Tambal", "Batik Tambal memiliki ciri khas dengan motif yang menyerupai potongan kain yang disatukan kembali. Motif ini melambangkan kehidupan yang penuh liku-liku dan keunikan. Warna-warna yang umum digunakan adalah biru, coklat, dan hijau.", "batik_bedjo"),
        Batik("Batik Parang Barong", "Motif Batik Parang Barong menggambarkan seekor barong, simbol dari keberanian dan kekuatan. Batik ini sering digunakan pada acara-acara adat dan memiliki warna-warna yang mencolok seperti merah, hitam, dan putih.", "batik_bedjo"),
        Batik("Batik Tambal", "Batik Tambal memiliki ciri khas dengan motif yang menyerupai potongan kain yang disatukan kembali. Motif ini melambangkan kehidupan yang penuh liku-liku dan keunikan. Warna-warna yang umum digunakan adalah biru, coklat, dan hijau.", "batik_bedjo"),
        Batik("Batik Parang Barong", "Motif Batik Parang Barong menggambarkan seekor barong, simbol dari keberanian dan kekuatan. Batik ini sering digunakan pada acara-acara adat dan memiliki warna-warna yang mencolok seperti merah, hitam, dan putih.", "batik_bedjo"),
        Batik("Batik Tambal", "Batik Tambal memiliki ciri khas dengan motif yang menyerupai potongan kain yang disatukan kembali. Motif ini melambangkan kehidupan yang penuh liku-liku dan keunikan. Warna-warna yang umum digunakan adalah biru, coklat, dan hijau.", "batik_bedjo"),
        Batik("Batik Parang Barong", "Motif Batik Parang Barong menggambarkan seekor barong, simbol dari keberanian dan kekuatan. Batik ini sering digunakan pada acara-acara adat dan memiliki warna-warna yang mencolok seperti merah, hitam, dan putih.", "batik_bedjo"),
        Batik("Batik Tambal", "Batik Tambal memiliki ciri khas dengan motif yang menyerupai potongan kain yang disatukan kembali. Motif ini melambangkan kehidupan yang penuh liku-liku dan keunikan. Warna-warna yang umum digunakan adalah biru, coklat, dan hijau.", "batik_bedjo")


        )

        // Set up RecyclerView adapter with the dummy data
        val adapter = ProductAdapter(batikList)
        binding.recyclerView.adapter = adapter

        return binding.root
    }
}
