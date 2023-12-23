package com.example.suitmedia_tes.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.suitmedia_tes.databinding.ActivityMainBinding
import com.example.suitmedia_tes.util.SharedPref

class MainActivity : AppCompatActivity(){
    private var sharedPrefKey: String = "nama"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonClicked()
    }
    fun isPalindrome(input: String): Boolean {
        val cleanInput = input.replace("\\s".toRegex(), "").toLowerCase()
        val reversedInput = cleanInput.reversed()
        return cleanInput == reversedInput
    }
    fun moveToNextScreen() {
        val moveNext = Intent(this@MainActivity, SecondScreen::class.java)
        SharedPref.init(this)
        val nama = binding.edtName.text.toString()
        SharedPref.write(sharedPrefKey, nama)
        startActivity(moveNext)
    }
    private fun checkPalindrome() {
        val inputPal = binding.edtPalindrome.text.toString().trim()
        val isPalindrome = isPalindrome(inputPal)

        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("Is This Palindrome")

            if (isPalindrome) {
                setMessage("Is Palindrome")
            } else {
                setMessage("Not Palindrome")
            }

            setPositiveButton("OK", null)
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }
    fun buttonClicked(){
        binding.btnCheck.setOnClickListener {
            if(binding.edtPalindrome.text.isNotEmpty()){
                checkPalindrome()
            }else{
                Toast.makeText(this, "Palindrome Still Empty", Toast.LENGTH_LONG).show()
            } }

        binding.btnNext.setOnClickListener {
            if(binding.edtName.text.isNotEmpty()){

                moveToNextScreen()
            }else{
                Toast.makeText(this, "Name Still Empty", Toast.LENGTH_LONG).show()
            }
        }
    }

}

