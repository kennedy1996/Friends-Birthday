package com.friendsbirthday.webservice

import com.friendsbirthday.dao.Friend
import retrofit2.Response
import retrofit2.http.*

interface FriendService {

    @GET("user")
    suspend fun searchAll(): List<FriendReturn>

    @PUT("user")
    suspend fun save(@Body nota: Friend): Response<Void>

    @DELETE("user/{id}")
    suspend fun remove(@Path("id") id: Int): Response<Void>

}