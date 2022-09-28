package com.friendsbirthday

class FriendsDao {

    fun add(friend: Friend){
        friends.add(Friend(friends.size+1, friend.name, friend.birthdate))
    }

    fun searchAll() : List<Friend> {
        return friends.toList()
    }
    fun modify(id: Int, friend: Friend) : List<Friend> {
        friends[id] = Friend(id, friend.name, friend.birthdate)
        return friends.toList()
    }
    fun remove(id: Int) : List<Friend> {
        friends.removeAt(id)
        return friends.toList()
    }

    companion object {
        private val friends = mutableListOf<Friend>(
            Friend(1, "Kennedy", "08/08"),
            Friend(2, "Danilo", "18/09")
        )
    }
}