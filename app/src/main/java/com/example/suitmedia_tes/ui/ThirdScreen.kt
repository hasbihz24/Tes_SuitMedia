package com.example.suitmedia_tes.ui

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.suitmedia_tes.Model.Data
import com.example.suitmedia_tes.Model.UserResponse
import com.example.suitmedia_tes.R
import com.example.suitmedia_tes.databinding.ActivityThirdScreenBinding
import com.example.suitmedia_tes.ui.Adapter.OnItemClickCallback
import com.example.suitmedia_tes.ui.Adapter.UserAdapter
import com.example.suitmedia_tes.util.Status
import com.example.suitmedia_tes.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class ThirdScreen : AppCompatActivity() {
    private lateinit var binding: ActivityThirdScreenBinding
    private var page = 1
    private var perPage = 10
    private val viewModel: MainViewModel by inject()
    private var name: String = ""
    private  var bottomRefreshing = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
       var actionBar = getSupportActionBar()
        if (actionBar != null) {

            // Customize the back button
            actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24);

            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);

            actionBar.title = "Third Screen"

            actionBar.setBackgroundDrawable(getDrawable(R.drawable.action_shape))
        }
        bottomRefreshing = false
        fetchUsersCoroutines(page,perPage)
        scrollListener()
    }
    fun scrollListener(){

        binding.rvUser.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager
                val visibleItemCount = layoutManager?.childCount
                val totalItemCount = layoutManager?.itemCount
                val firstVisibleItemPosition =
                    (layoutManager as LinearLayoutManager?)?.findFirstVisibleItemPosition()
                    // Check if the RecyclerView is at the bottom and not already refreshing
                    if (page == 1 &&
                        visibleItemCount != null && totalItemCount != null && firstVisibleItemPosition != null
                    ) {
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                            val timer = object: CountDownTimer(2000, 1000) {
                                override fun onTick(millisUntilFinished: Long) {
                                    binding.swipeRefreshLayout.isRefreshing = true
                                }
                                override fun onFinish() {
                                    binding.swipeRefreshLayout.isRefreshing = false
                                    page = 2
                                    fetchUsersCoroutines(page, perPage)
                                }
                            }
                            timer.start()
                        }
                    }
                binding.swipeRefreshLayout.setOnRefreshListener {
                    if (page == 2){
                    val timer = object: CountDownTimer(2000, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            binding.swipeRefreshLayout.isRefreshing = true
                        }
                        override fun onFinish() {
                            page = 1
                            fetchUsersCoroutines(page, perPage)
                            binding.swipeRefreshLayout.isRefreshing = false
                        }
                    }
                    timer.start()
                    }
                }
            }
        })
    }

    private fun fetchUsersCoroutines(page: Int, perPage: Int) {
        viewModel.getUser(page, perPage).observe(this){
            when(it.status){
                Status.SUCCESS ->{
                    binding.progressBar.visibility = View.GONE
                    setUPRecycleView(it.data)
                    Log.d("Error", "${it.data}")
                }
                Status.ERROR ->{
                    Log.d("Error", "Error Occured!")
                    Log.d("Error", "${it.message}")
                }
                Status.LOADING ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun setUPRecycleView(data: UserResponse?) {
        val adapter = UserAdapter()
        adapter.setOnItemClickCallback(object : OnItemClickCallback{
            override fun onItemClicked(data: Data) {
                name = "${data.first_name} ${data.last_name}"
                val moveNext = Intent(this@ThirdScreen, SecondScreen::class.java)
                moveNext.putExtra("nama_api",name)
                startActivity(moveNext)
            }

        })
        adapter.submitCoursesResponse(data?.data ?: emptyList())
        binding.rvUser.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvUser.adapter = adapter

    }

    private fun loadNextPageData(i: Int) {
        page = i
        fetchUsersCoroutines(page,perPage)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}



