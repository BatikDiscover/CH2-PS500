//package com.bangkit.batikdiscover.ui.notifications
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.bangkit.batikdiscover.databinding.FragmentNotificationBinding
//
//class NotificationsFragment : Fragment() {
//
//    private var _binding: FragmentNotificationBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View {
//        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        // Load notifications from JSON
//        val notificationList = getDummyNotif()
//
//        // Set up RecyclerView adapter with the notification data
//        val adapter = NotificationAdapter(notificationList)
//        binding.recyclerViewNotif.adapter = adapter
//
//        // Set up LinearLayoutManager
//        binding.recyclerViewNotif.layoutManager = LinearLayoutManager(requireContext())
//
//        return root
//    }
//
//    private fun getDummyNotif(): List<Notifikasi> {
//        return listOf(
//            Notifikasi("Notifikasi 1", "12-01-2023"),
//            Notifikasi("Notifikasi 2", "13-01-2023"),
//            Notifikasi("Notifikasi 3", "14-01-2023"),
//            Notifikasi("Notifikasi 4", "15-01-2023"),
//            Notifikasi("Notifikasi 5", "16-01-2023"),
//            Notifikasi("Notifikasi 6", "17-01-2023"),
//            Notifikasi("Notifikasi 7", "18-01-2023"),
//            Notifikasi("Notifikasi 8", "19-01-2023"),
//            Notifikasi("Notifikasi 9", "20-01-2023"),
//            Notifikasi("Notifikasi 10", "21-01-2023")
//        )
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//}
