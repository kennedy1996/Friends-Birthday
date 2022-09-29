package com.friendsbirthday

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.friendsbirthday.databinding.ItemFriendBinding

class ListFriendsAdapter(
    private val context: Context,
    produtos: List<Friend>,
    private val dao: FriendDao
) : RecyclerView.Adapter<ListFriendsAdapter.ViewHolder>() {

    private val friends = produtos.toMutableList()


    inner class ViewHolder(private val binding: ItemFriendBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun linking(
            friend: Friend,
            context: Context,
            dao: FriendDao,
            position: Int
        ) {
            val name = binding.itemFriendName
            name.text = friend.name
            val birthday = binding.itemFriendBirthday
            birthday.text = friend.birthdate

            binding.itemFriendModify.setOnClickListener {
                showDialogFriendModify(context, friends[position], dao, friends[position].id)
            }
            binding.itemFriendDelete.setOnClickListener {
                update(dao.remove(friends[position].id))
                update(dao.searchAll())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemFriendBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend = friends[position]
        holder.linking(friend, context, dao, position)
    }

    override fun getItemCount(): Int = friends.size

    fun update(friends: List<Friend>) {
        this.friends.clear()
        this.friends.addAll(friends)
        notifyDataSetChanged()
    }

    fun showDialogFriendModify(
        context: Context,
        friend: Friend,
        dao: FriendDao,
        idFriend: Int
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
        val buttonUpdate: Button =
            dialog.findViewById(R.id.dialog_friend_button_save) as Button
        friendName.setText(friend.name)
        friendBirthday.setText(friend.birthdate)
        buttonUpdate.text=context.getString(R.string.update)

        buttonUpdate.setOnClickListener{
            dao.modify(idFriend, Friend(idFriend, friendName.text.toString(), friendBirthday.text.toString()))
            update(dao.searchAll())
            dialog.dismiss()
        }
    }
}