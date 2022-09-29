package com.friendsbirthday

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Button
import android.widget.EditText

fun dialogNewFriend(
    context: Context,
    dao: FriendsDao,
    adapter: ListFriendsAdapter,
) {
    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(true)
    dialog.setContentView(R.layout.dialog_friend)
    dialog.show()

    val friendName: EditText =
        dialog.findViewById(R.id.dialog_friend_name) as EditText
    val friendBirthday: EditText =
        dialog.findViewById(R.id.dialog_friend_birthday) as EditText
    val buttonSave: Button =
        dialog.findViewById(R.id.dialog_friend_button_save) as Button

    friendName.hint = context.getString(R.string.name)
    friendBirthday.hint = context.getString(R.string.birthday)

    buttonSave.text = context.getString(R.string.save)
    buttonSave.setOnClickListener {
        dao.add(Friend(name = friendName.text.toString(), birthdate = friendBirthday.text.toString()))
        adapter.update(dao.searchAll())
        dialog.dismiss()
    }
}