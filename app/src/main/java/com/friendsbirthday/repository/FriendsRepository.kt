package com.friendsbirthday.repository

import com.friendsbirthday.dao.Friend
import com.friendsbirthday.dao.FriendDao
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
        val newFriend = Friend(dao.nextId(), friend.name, friend.birthdate)
        if (webClient.add(newFriend)) {
            dao.add(newFriend)
        }
    }

    suspend fun modify(friend: Friend) {
        if (webClient.modify(friend)) {
            dao.modify(friend)
        }
    }

    suspend fun sync() {
        if (webClient.searchAll() != null) {
            dao.addAllNew(webClient.searchAll()!!)
        }
    }
}