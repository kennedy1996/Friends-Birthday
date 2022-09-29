package com.friendsbirthday

import android.os.Bundle
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
        clickingAddButton()
        usingSearch()
    }

    private fun clickingAddButton() {
        binding.activityFriendsListFab.setOnClickListener {
            dialogNewFriend(this, dao, adapter)
        }
    }

    private fun usingSearch() {
        binding.activityFriendsSearchText.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
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