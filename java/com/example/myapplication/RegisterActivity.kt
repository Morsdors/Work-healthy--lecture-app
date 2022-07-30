package com.example.myapplication

import kotlinx.android.synthetic.main.*     //content_main.

import DBHelper
//import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

//import com.example.myapplication.LoginActivity  //
//import com.example.myapplication.RegisterActivity

class RegisterActivity : AppCompatActivity() {


    lateinit var handler:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val register = findViewById<Button>(R.id.register)
        val back = findViewById<Button>(R.id.register_back)
        val name = findViewById<EditText>(R.id.name)
        val password = findViewById<EditText>(R.id.password)
        handler = DBHelper(this)


        register.setOnClickListener {
            handler.insertUserData(name.text.toString(), password.text.toString())

            Toast.makeText(this, "Poprawna rejestracja", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        back.setOnClickListener {
            val intent = Intent(this@RegisterActivity, enterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


}