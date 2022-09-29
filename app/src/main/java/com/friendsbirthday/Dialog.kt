package com.friendsbirthday

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Button
import android.widget.EditText
import com.friendsbirthday.repository.FriendsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun dialogNewFriend(
    context: Context,
    dao: FriendDao,
    adapter: ListFriendsAdapter,
    repository: FriendsRepository,
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
        CoroutineScope(Dispatchers.IO).launch {
            repository.add(Friend(name = friendName.text.toString(), birthdate = friendBirthday.text.toString()))
            dialog.dismiss()
            withContext(Dispatchers.Main) {}
        }

    }
}