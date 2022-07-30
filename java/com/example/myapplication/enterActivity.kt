package com.example.myapplication

import kotlinx.android.synthetic.main.*     //content_main.

import DBHelper
//import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

//import com.example.myapplication.LoginActivity  //
//import com.example.myapplication.RegisterActivity

class enterActivity : AppCompatActivity() {


    lateinit var handler:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter)
        val log_in = findViewById<Button>(R.id.loggingin)
        val ranking = findViewById<Button>(R.id.ranking)
        val registration = findViewById<Button>(R.id.registration)
        handler = DBHelper(this)
        //handler.cleanDB()


        log_in.setOnClickListener {
            Toast.makeText(this, "logowanie", Toast.LENGTH_SHORT).show()

            Handler(Looper.getMainLooper()).postDelayed(Runnable{
                val intent = Intent(this@enterActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 100)
            /*
            val intent = Intent(this@enterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()*/
            //showLogin()
        }

        registration.setOnClickListener {
            val intent = Intent(this@enterActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        ranking.setOnClickListener {
            val intent = Intent(this@enterActivity, RankingActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


}