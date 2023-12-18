package com.bangkit.batikdiscover.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.batikdiscover.MainActivity
import com.bangkit.batikdiscover.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            // Implement your login logic here
            // For example, if login is successful, navigate to DashboardFragment
            navigateToDashboard()
        }

        binding.textViewDaftar.setOnClickListener {
            // Handle the click on the registration link
            // You can navigate to the registration screen or perform any other action
        }
    }

    private fun navigateToDashboard() {
        // Create an Intent to navigate to MainActivity (assuming DashboardFragment is part of MainActivity)
        val intent = Intent(this, MainActivity::class.java)
        // Add any data you need to pass to the DashboardFragment
        // For example, you can pass the user's information
        // Start the activity
        startActivity(intent)
        // Finish LoginActivity so the user cannot navigate back to it using the back button
        finish()
    }
}
