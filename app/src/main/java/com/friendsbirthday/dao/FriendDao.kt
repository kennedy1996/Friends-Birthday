package com.friendsbirthday.dao

class FriendDao {

    fun addAllNew(listFriend: List<Friend>) {
        friends.clear()
        friends.addAll(listFriend)
    }
    fun add(friend: Friend) {
        friends.add(Friend(friends.size + 1, friend.name, friend.birthdate))
    }

    fun nextId(): Int {
        var higher=0
        for(i in friends.indices){
            if(friends[i].id>higher){
                higher= friends[i].id
            }
        }
        return higher+1
    }

    fun searchAll(): List<Friend> {
        return friends.toList()
    }

    fun modify(friend: Friend): List<Friend> {
        friends[searchId(friend.id)] = friend
        return friends.toList()
    }

    fun remove(id: Int): List<Friend> {
        friends.removeAt(searchId(id))
        return friends.toList()
    }

    fun searchId(idFriend: Int): Int {
        var vReturn = 0
        for(i in friends.indices){
            if(friends[i].id == idFriend) vReturn= i
        }
        return vReturn
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