package com.example.suitmedia_tes

import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class third_screen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var apiService: ApiService

    private var page = 1
    private val perPage = 10
    private var isLoading = false
    private var isLastPage = false

    private val users: MutableList<User> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_screen)
       var actionBar = getSupportActionBar()
        if (actionBar != null) {

            // Customize the back button
            actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24);

            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);

            actionBar.title = "Third Screen"

            actionBar.setBackgroundDrawable(getDrawable(R.drawable.action_shape))
        }
        recyclerView = findViewById(R.id.rv_user)
        adapter = UserAdapter(users)
        layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        apiService = ApiService.create()

        fetchData()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLastPage && !isLoading && layoutManager.findLastVisibleItemPosition() == users.size - 1) {
                    page++
                    fetchData()
                }
            }
        })
    }

    private fun fetchData() {
        isLoading = true
        apiService.getUsers(page, perPage).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    val fetchedUsers = userResponse?.data ?: emptyList()
                    users.addAll(fetchedUsers)
                    adapter.notifyDataSetChanged()

                    isLoading = false

                    if (fetchedUsers.isEmpty()) {
                        isLastPage = true
                    }
                } else {
                    // Handle error cases
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                // Handle failure cases
            }
        })
    }

    override fun onBackPressed() {
        val selectedUserName = adapter.getSelectedUserName()
        val data = Intent()
        data.putExtra("selectedUserName", selectedUserName)
        setResult(RESULT_OK, data)
        super.onBackPressed()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val selectedUserName = adapter.getSelectedUserName()
                val data = Intent()
                data.putExtra("selectedUserName", selectedUserName)
                setResult(RESULT_OK, data)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }




}



