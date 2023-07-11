package com.example.suitmedia_tes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondScreen : AppCompatActivity() {
    private lateinit var Tvnama : TextView
    private lateinit var BtnUser : Button
    private lateinit var SelectedUser : TextView
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

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


        Tvnama = findViewById(R.id.tv_nama)
        BtnUser =  findViewById(R.id.btn_user)
        SelectedUser = findViewById(R.id.tv_selectedUser)

        val nama = intent.getStringExtra("nama")
        Tvnama.text = nama.toString().trim()

        BtnUser.setOnClickListener {
            val intent = Intent(this@SecondScreen, third_screen::class.java)
            startActivityForResult(intent, REQUEST_CODE_SELECT_USER)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_USER && resultCode == RESULT_OK) {
            val selectedUserName = data?.getStringExtra("selectedUserName")
            SelectedUser.text = selectedUserName
        }
    }

    companion object {
        private const val REQUEST_CODE_SELECT_USER = 1001
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