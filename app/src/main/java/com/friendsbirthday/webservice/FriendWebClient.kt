package com.friendsbirthday.webservice

import android.util.Log
import com.friendsbirthday.Friend


private const val TAG = "FriendWebService"

class FriendWebClient {

    private val friendService: FriendService =
        InitializatorRetrofit().friendService

    suspend fun searchAll(): List<Friend>? {
        return try {
            val friendsReturn = friendService
                .searchAll()
            friendsReturn.map { friendReturn ->
                friendReturn.friend
            }
        } catch (e: Exception) {
            Log.e(TAG, "searchAll: ", e)
            null
        }
    }

    suspend fun add(friend: Friend): Boolean {
        try {
            val friend = friendService.save(friend)
            return friend.isSuccessful
        } catch (e: Exception) {
            Log.e(TAG, "save: error trying to save", e)
        }
        return false
    }

    suspend fun remove(id: Int): Boolean {
        try {
            friendService.remove(id)
            return true
        } catch (e: Exception) {
            Log.e(TAG, "remove: error trying to remove", e)
        }
        return false
    }
}