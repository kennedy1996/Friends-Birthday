package com.friendsbirthday.webservice

import com.friendsbirthday.dao.Friend

class FriendReturn(
    val name: String?,
    val birthdate: String?,
    val id: Int,
) {

    val friend: Friend
        get() = Friend(
        id = id,
        birthdate = birthdate ?: "",
        name = name ?: "",
    )

}