package com.example.suitmedia_tes.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmedia_tes.R
import com.example.suitmedia_tes.databinding.ActivityMainBinding
import com.example.suitmedia_tes.databinding.ActivitySecondScreenBinding
import com.example.suitmedia_tes.databinding.ActivityThirdScreenBinding
import com.example.suitmedia_tes.util.SharedPref

class SecondScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding
    private var sharedPrefKey: String = "nama"
    private var nama = ""
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = this.resources.getColor(R.color.white)

        var actionBar = getSupportActionBar()
        if (actionBar != null) {

            // Customize the back button
            actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24);

            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);

            actionBar.title = "Second Screen"

            actionBar.setBackgroundDrawable(getDrawable(R.drawable.action_shape))
        }


        val Tvnama = binding.tvNama
        val BtnUser =  binding.btnUser
        val SelectedUser = binding.tvSelectedUser
        SharedPref.init(this)
        val nama = SharedPref.read(sharedPrefKey, nama)
        Tvnama.text = nama.toString().trim()
        val nama_api = intent.getStringExtra("nama_api")
        if (nama_api != null){
            SelectedUser.text = nama_api.toString()
            SelectedUser.visibility = View.VISIBLE
        }else{
            SelectedUser.text = "Selected User Name"
        }

        BtnUser.setOnClickListener {
            val intent = Intent(this@SecondScreen, ThirdScreen::class.java)
            startActivity(intent)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this@SecondScreen, MainActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@SecondScreen, MainActivity::class.java)
        startActivity(intent)
    }
}