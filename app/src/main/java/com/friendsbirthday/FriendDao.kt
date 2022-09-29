package com.friendsbirthday

import android.util.Log

class FriendDao {

    fun addAllNew(listFriend: List<Friend>) {
        Log.i("testeNovos", "size Antes: ${listFriend.size}")
        friends.clear()
        friends.addAll(listFriend)
        Log.i("testeNovos", "size Depois: ${listFriend.size}")
    }
    fun add(friend: Friend) {
        friends.add(Friend(friends.size + 1, friend.name, friend.birthdate))
    }

    fun searchAll(): List<Friend> {
        return friends.toList()
    }

    fun modify(id: Int, friend: Friend): List<Friend> {
        friends[searchId(friend.id)] = friend
        return friends.toList()
    }

    fun remove(id: Int): List<Friend> {
        friends.removeAt(searchId(id))
        return friends.toList()
    }

    fun searchId(idFriend: Int): Int {
        var returnF = 0
        for(i in friends.indices){
            if(friends[i].id == idFriend) returnF= i
        }
        return returnF
    }
    fun search(name: String): List<Friend> {
        var vReturn = friends.toList()
        if (name != "") {
            for (i in friends.indices) {
                if (friends[i].name.contains(name, true)) {
                    vReturn = mutableListOf(friends[i])
                }
            }
        }
        return vReturn
    }

    companion object {
        private val friends = mutableListOf<Friend>()
    }
}