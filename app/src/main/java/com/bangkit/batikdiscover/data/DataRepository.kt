package com.bangkit.batikdiscover.data

import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onEach
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class DataRepository(private val context: Context) {

    private val apiService = ApiConfig.getApiService()
    private val apiServiceMl = ApiMl.getApiService()
    private val userPreference = UserPreference(context)

    suspend fun login(email: String, password: String): LoginResponse {
        val response = apiService.login(email, password)
        if (response.message == "success" && response.loginResult != null) {
            saveUserToken(response.loginResult.token)
            Log.d("UserRepository", "User token saved: ${response.loginResult.token}")
        }
        return response
    }
    suspend fun register(firstName: String, lastName: String, email: String, password: String): RegisterResponse {
        return apiService.register(firstName, lastName, email, password)
    }

    suspend fun saveUserToken(token: String) {
        userPreference.saveUserToken(token)
        Log.d("UserRepository", "User token saved: $token")
    }

    fun getUserToken(): Flow<String?> {
        return userPreference.userToken.onEach { token ->
            Log.d("UserRepository", "User token retrieved: $token")
        }
    }

    suspend fun getUserById(userId: String): UserResponse {
        try {
            val userToken = getUserToken().firstOrNull()
            if (userToken != null) {
                return apiService.getUserById("Bearer $userToken", userId)
            } else {
                throw UploadFailedException("User token is null")
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error when fetching user by ID: ${e.message}")
            throw e
        }
    }


    suspend fun getCommunityById(token: String, CommunityId: String): CommunityDetailItem? {
        return try {
            Log.d("DataRepository", "Fetching community details for id: $CommunityId")

            val response = apiService.getCommunityById(token, CommunityId)

            Log.d("DataRepository", "API Response: $response")

            return if (response.status == "success") {
                Log.d("DataRepository", "Community details retrieved successfully")
                response.CommunityData.postdetail
            } else {
                Log.e("DataRepository", "API returned error: ${response.status}")
                // Handle error case
                null
            }
        } catch (e: Exception) {
            Log.e("DataRepository", "Error fetching community details", e)
            // Handle the exception
            null
        }
    }

    suspend fun getCommunityPosts(token: String): CommunityResponse {
    return apiService.getCommunityPost("Bearer $token")
}

    suspend fun uploadCommunity(
        token: String,
        image: MultipartBody.Part,
        title: String,
        content: String,
    ) {
        try {
            val response = apiService.uploadCommunity("Bearer $token", image, title, content)

            if (response.status == "success") {
                // Upload success
                Log.d("UserRepository", "Community uploaded successfully: ${response.status}")
            } else {
                // Upload failed
                Log.e("UserRepository", "Error uploading community: ${response.status}")
                throw UploadFailedException(response.status)
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error when uploading community: ${e.message}")
            throw e
        }
    }

    suspend fun addCommentToPost(token: String, postId: String, content: String): CommentResponse {
        val commentRequest = CommentRequest(content)
        return apiService.addCommentToPost("Bearer $token", postId, commentRequest)
    }

    // Get comments for a specific post
    suspend fun getCommentsByPostId(token: String, postId: String): List<CommentItem> {
        val response = apiService.getCommentsByPostId("Bearer $token", postId)

        return if (response.status == "success") {
            response.commentData.comments // Sesuaikan dengan nama field yang sesuai dari CommentResponse
        } else {
            // Handle error case
            emptyList() // Atau sesuaikan dengan cara Anda menangani kasus error
        }
    }

    suspend fun getArticles(token: String): ArticleListResponse {
        return apiService.getArticles("Bearer $token")
    }
    suspend fun getArticleById(token: String, articleId: String): ArticleItemDetail? {
        val response = apiService.getArticleById("Bearer $token", articleId)

        return if (response.status == "success") {
            response.articleData.article
        } else {
            // Handle error case
            null
        }
    }


    suspend fun getEvents(token: String): EventListResponse {
        return apiService.getEvents("Bearer $token")
    }
    suspend fun getEventById(token: String, eventId: String): EventItem? {
        val response = apiService.getEventById(token, eventId)

        return if (response.status == "success") {
            response.eventData.event
        } else {
            // Handle error case
            null
        }
    }

    suspend fun getBatiks(token: String): BatikListResponse {
        return apiService.getBatiks("Bearer $token")
    }
    suspend fun getBatikById(token: String, batikId: String): BatikDetailItem? {
        try {
            val response = apiService.getBatikById("Bearer $token", batikId)

            return if (response.status == "success") {
                response.batikDetailData.batik
            } else {
                // Handle error case
                null
            }
        } catch (e: Exception) {
            Log.e("DataRepository", "Error when fetching batik by ID: ${e.message}")
            throw e
        }
    }


    //Predict
    suspend fun getPredict(imageFile: File): PredictionResponse {
        val requestFile: RequestBody = imageFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", imageFile.name, requestFile)

        try {
            // Log request details
            Log.d("PredictionRequest", "File name: ${imageFile.name}")

            // Make the API call
            val response = apiServiceMl.predict(body)

            // Log response details

            return response
        } catch (e: Exception) {
            // Log any exceptions
            Log.e("PredictionRequest", "Error making prediction request", e)
            throw e
        }
    }



    class UploadFailedException(message: String?) : Exception(message)
}