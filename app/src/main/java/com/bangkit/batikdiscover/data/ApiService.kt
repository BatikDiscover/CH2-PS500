package com.bangkit.batikdiscover.data

import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    @GET("user/{userId}")
    suspend fun getUserById(
        @Header("Authorization") token: String,
        @Path("userId") userId: String
    ): UserResponse
    // Predict
    @Multipart
    @POST("predicts")
    suspend fun predict(
        @Part image: MultipartBody.Part
    ): PredictionResponse


    // Auth endpoints
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("firstName") firstName: String,
        @Field("lastName") lastName: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse


    // Community endpoints
    @GET("post/{id}")
    suspend fun getCommunityById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): CommunityDetailResponse

    @GET("post")
    suspend fun getCommunityPost(
        @Header("Authorization") token: String
    ): CommunityResponse

    @POST("post")
    @Multipart
    suspend fun uploadCommunity(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part,
        @Part("title") title: String,
        @Part("content") content: String,
    ): CommunityResponse

    @POST("post/{postId}/comment")
    suspend fun addCommentToPost(
        @Header("Authorization") token: String,
        @Path("postId") postId: String,
        @Body commentRequest: CommentRequest
    ): CommentResponse

    @GET("post/{postId}/comment")
    suspend fun getCommentsByPostId(
        @Header("Authorization") token: String,
        @Path("postId") postId: String
    ): CommentResponse


    // Article endpoint
    @GET("article")
    suspend fun getArticles(
        @Header("Authorization") token: String
    ): ArticleListResponse

    @GET("article/{id}")
    suspend fun getArticleById(
        @Header("Authorization") token: String,
        @Path("id") articleId: String
    ): ArticleDetailResponse
    // Event endpoint
    @GET("event")
    suspend fun getEvents(
        @Header("Authorization") token: String
    ): EventListResponse
    @GET("event/{eventId}")
    suspend fun getEventById(
        @Header("Authorization") token: String,
        @Path("eventId") eventId: String
    ): EventItemResponse

    //batik
    @GET("batik")
    suspend fun getBatiks(
        @Header("Authorization") token: String
    ): BatikListResponse

    @GET("batik/{batikId}")
    suspend fun getBatikById(
        @Header("Authorization") token: String,
        @Path("batikId") batikId: String
    ): BatikDetailResponse
}
