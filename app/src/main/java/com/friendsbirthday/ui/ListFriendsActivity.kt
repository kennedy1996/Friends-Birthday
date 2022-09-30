package com.friendsbirthday.ui

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.friendsbirthday.dao.FriendDao
import com.friendsbirthday.ui.adapter.ListFriendsAdapter
import com.friendsbirthday.databinding.ActivityFriendsListBinding
import com.friendsbirthday.dialogNewFriend
import com.friendsbirthday.repository.FriendsRepository
import com.friendsbirthday.webservice.FriendWebClient
import kotlinx.coroutines.launch

class ListFriendsActivity : AppCompatActivity() {

    private val dao = FriendDao()
    private val binding by lazy {
        ActivityFriendsListBinding.inflate(layoutInflater)
    }
    private val repository by lazy {
        FriendsRepository(dao,FriendWebClient()
        )
    }
    private val adapter = ListFriendsAdapter(context = this, produtos = dao.searchAll(), dao = dao, repository= repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        clickingAddButton()
        usingSearch()

        lifecycleScope.launch {
            launch {
                sync()
            }
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                configRecyclerView()
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun clickingAddButton() {
        binding.activityFriendsListFab.setOnClickListener {
                dialogNewFriend(this, dao, adapter, repository)

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

    private suspend fun sync() {
        repository.sync()
        adapter.update(dao.searchAll())
    }
}