package com.friendsbirthday.webservice

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class InitializatorRetrofit {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://hello-world.innocv.com/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val friendService = retrofit.create(FriendService::class.java)

}