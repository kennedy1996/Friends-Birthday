package com.friendsbirthday

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.friendsbirthday.databinding.ActivityFriendsListBinding

class ListFriendsActivity : AppCompatActivity() {

    private val dao = FriendsDao()
    private val adapter = ListFriendsAdapter(context = this, produtos = dao.searchAll(), dao = dao)
    private val binding by lazy {
        ActivityFriendsListBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configRecyclerView()

        binding.activityFriendsListFab.setOnClickListener {
            val dialog = Dialog(this)
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

            friendName.hint="Name"
            friendBirthday.hint="Birthday"

            buttonSave.setOnClickListener {
                dao.add(Friend(5, friendName.text.toString(), friendBirthday.text.toString()))
                adapter.update(dao.searchAll())
                dialog.dismiss()
            }
        }

        binding.activityFriendsSearchText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.update(dao.search(newText!!))
                return false
            }
        })



    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.searchAll())
    }

    private fun configRecyclerView() {
        val recyclerView = binding.activityFriendsListRecyclerview
        recyclerView.adapter = adapter
    }
}