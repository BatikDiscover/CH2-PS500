//package com.bangkit.batikdiscover.ui.notifications
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bangkit.batikdiscover.R
//import com.bangkit.batikdiscover.databinding.ItemNotificationBinding
//
//class NotificationAdapter(private val notifications: List<Notifikasi>) :
//    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
//
//    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val binding = ItemNotificationBinding.bind(itemView)
//
//        fun bind(notification: Notifikasi) {
//            binding.apply {
//                judulNotif.text = notification.judul
//                tanggalNotif.text = notification.tanggal
//                // Set your image for the notification icon here (if needed)
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_notification, parent, false)
//        return NotificationViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
//        val notification = notifications[position]
//        holder.bind(notification)
//    }
//
//    override fun getItemCount(): Int {
//        return notifications.size
//    }
//}
