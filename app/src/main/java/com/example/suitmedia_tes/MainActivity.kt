package com.example.suitmedia_tes

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtName : EditText
    private lateinit var edtPal : EditText
    private lateinit var btncheck : Button
    private lateinit var btnNext : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtName = findViewById(R.id.tv_name)
        edtPal = findViewById(R.id.tv_palindrome)
        btncheck = findViewById(R.id.btn_check)
        btnNext = findViewById(R.id.btn_next)

        btncheck.setOnClickListener(this)
        btnNext.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_next->{
                val movenext = Intent(this@MainActivity, SecondScreen::class.java)
                movenext.putExtra("nama", edtName.text.toString().trim())
                startActivity(movenext)
            }
            R.id.btn_check->{
                    val  inputPal = edtPal.text.toString().trim()
                    var isTrue = false
                    when(inputPal){
                        "kasur rusak"->{
                            isTrue = true
                        }
                        "step on no pets"->{
                            isTrue = true
                        }
                        "put it up"->{
                            isTrue = true
                        }
                        "suttmedia"->{
                            isTrue=false
                        }
                    }
                if (isTrue == true){
                    val builder = AlertDialog.Builder(this)
                    with(builder){
                        setTitle("Is This Palindrome")
                        setMessage("IsPalindrome")
                        setPositiveButton("OK", null)
                    }
                    val alertDialog = builder.create();
                    alertDialog.show()
                }else{
                    val builder = AlertDialog.Builder(this)
                    with(builder){
                        setTitle("Is This Palindrome")
                        setMessage("Not Palindrome")
                        setPositiveButton("OK", null)
                    }
                    val alertDialog = builder.create();
                    alertDialog.show()
                }
            }
        }
    }
}