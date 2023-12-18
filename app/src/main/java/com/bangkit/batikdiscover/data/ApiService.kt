package com.bangkit.batikdiscover.data

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
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

//    @GET("event")
//    @Multipart
//    suspend fun uploadStory(
//        @Header("Authorization") token: String,
//        @Part("description") description: String,
//        @Part("lat") lat: Float?,
//        @Part("lon") lon: Float?,
//        @Part photo: MultipartBody.Part,
//    ): StoryResponse
//
//
//
//    @GET("stories/{id}")
//    suspend fun getStoryDetail(
//        @Header("Authorization") token: String,
//        @Path("id") storyId: String
//    ): StoryDetailResponse

}