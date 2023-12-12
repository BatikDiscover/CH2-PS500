package com.bangkit.batikdiscover.data

import android.content.Context
import android.content.Intent
import android.util.Log

class DataRepository(private val context: Context) {

    var apiService = ApiConfig.getApiService()
    suspend fun register(name: String, email: String, password: String): RegisterResponse {
        return apiService.register(name, email, password)
    }

    suspend fun login(email: String, password: String, context: Context): LoginResponse {
        val response = apiService.login(email, password)

        if (response.message == "success") {

            // Jika login sukses, arahkan pengguna ke DashboardActivity
            val intent = Intent(context, DashboardActivity::class.java)
            context.startActivity(intent)
        }

        return response
    }
    suspend fun register(firstName: String, lastName: String, password: String): RegisterResponse {
        return apiService.register(name, email, password)
    }
}