package com.bangkit.batikdiscover.data

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import java.io.Serializable

data class RegisterResponse(
    var message: String?
)

data class PredictionResponse(
    @SerializedName("nama") val nama: String)



data class LoginResponse(
    val message: String,
    val loginResult: LoginResult
)

data class LoginResult(
    val token: String,
    val userId: String
)

//user
data class UserResponse(
    @SerializedName("status") val status: String,
    @SerializedName("data") val userData: UserData
)

data class UserData(
    @SerializedName("user") val user: UserItem
)

data class UserItem(
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("email") val email: String
)



//community

data class CommunityDetailResponse(
    @SerializedName("status") val status: String,
    @SerializedName("data") val CommunityData: CommunityData
)
data class CommunityData(
    @SerializedName("posting") val postdetail: CommunityDetailItem
)
data class CommunityDetailItem(
    @SerializedName("date") val date: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("time") val time: String,
    @SerializedName("title") val title: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("content") val content: String
)

data class CommunityResponse(
    @SerializedName("status") val status: String,
    @SerializedName("data") val postData: PostingsData
)

data class PostingsData(
    @SerializedName("postings") val postings: List<PostingsItem>
)

data class PostingsItem(
    @SerializedName("id") val id: String,
    @SerializedName("date") val date: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("title") val title: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("content") val content: String
)

data class CommentResponse(
    @SerializedName("status") val status: String,
    @SerializedName("data") val commentData: CommentData
)
data class CommentRequest(
    @SerializedName("content") val content: String
)

data class CommentData(
    @SerializedName("comment") val comments: List<CommentItem>
)

data class CommentItem(
    @SerializedName("id") val id: String,
    @SerializedName("date") val date: String,
    @SerializedName("time") val time: String,
    @SerializedName("postId") val postId: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("content") val content: String
)




    //Artikel
    data class ArticleDetailResponse(
        @SerializedName("status") val status: String,
        @SerializedName("data") val articleData: ArticleData
    )

data class ArticleData(
    @SerializedName("article") val article: ArticleItemDetail
)

data class ArticleItemDetail(
    @SerializedName("date") val date: String,
    @SerializedName("author") val author: String,
    @SerializedName("source") val source: String,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String
)

    data class ArticleListResponse(
        @SerializedName("status") val status: String,
        @SerializedName("data") val articleListData: ArticleListData
    )

    data class ArticleListData(
        @SerializedName("articles") val articles: List<ArticleItem>
    )

    data class ArticleItem(
        @SerializedName("id") val id: String,
        @SerializedName("date") val date: String,
        @SerializedName("author") val author: String,
        @SerializedName("source") val source: String,
        @SerializedName("title") val title: String,
        @SerializedName("content") val content: String
)

    //Events

data class EventItemResponse(
    @SerializedName("status") val status: String,
    @SerializedName("data") val eventData: EventItemData
)

data class EventItemData(
    @SerializedName("event") val event: EventItem
)
    data class EventListResponse(
        @SerializedName("status") val status: String,
        @SerializedName("data") val eventListData: EventListData
    )

    data class EventListData(
        @SerializedName("events") val events: List<EventItem>
    )

    data class EventItem(
        @SerializedName("id") val id: String,
        @SerializedName("date") val date: String,
        @SerializedName("name") val name: String,
        @SerializedName("description") val description: String,
        @SerializedName("location") val location: String,
        @SerializedName("imageUrl") val imageEventItem: String

    )

    //Batik
    data class BatikDetailResponse(
        @SerializedName("status") val status: String,
        @SerializedName("data") val batikDetailData: BatikDetailData
    )

data class BatikDetailData(
    @SerializedName("batik") val batik: BatikDetailItem
)
data class BatikDetailItem(
    @SerializedName("id") val id: String,
    @SerializedName("asal") val origin: String,
    @SerializedName("pola") val pattern: String,
    @SerializedName("nama") val name: String,
    @SerializedName("makna") val meaning: String,
    @SerializedName("imageUrl") val imageUrl: String
)
    data class BatikListResponse(
        @SerializedName("status") val status: String,
        @SerializedName("data") val batikListData: BatikListData
    )

data class BatikListData(
    @SerializedName("batik") val batiks: List<BatikItem>
)

data class BatikItem(
    @SerializedName("id") val id: String,
    @SerializedName("asal") val origin: String,
    @SerializedName("pola") val pattern: String,
    @SerializedName("nama") val name: String,
    @SerializedName("makna") val meaning: String,
    @SerializedName("imageUrl") val imageUrl: String,
)
