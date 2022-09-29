package com.friendsbirthday.repository

import android.util.Log
import com.friendsbirthday.Friend
import com.friendsbirthday.FriendDao
import com.friendsbirthday.webservice.FriendWebClient

class FriendsRepository(
    private val dao: FriendDao,
    private val webClient: FriendWebClient
) {

    fun searchAll(): List<Friend> {
        return dao.searchAll()
    }

    suspend fun remove(id: Int) {
        if (webClient.remove(id)) {
            dao.remove(id)
        }
    }

    suspend fun add(friend: Friend) {
        if (webClient.add(friend)) {
            dao.add(friend)
        }
    }

    suspend fun sync() {
        if (webClient.searchAll() != null) {
            dao.addAllNew(webClient.searchAll()!!)
        }
    }
}