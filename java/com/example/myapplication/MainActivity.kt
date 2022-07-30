package com.example.myapplication

import DBHelper
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        var counter = 0 //liczba strzałów
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val start= findViewById<Button>(R.id.start_button)
        //val settings= findViewById<Button>(R.id.settings_button)
        val log_out_button= findViewById<Button>(R.id.log_out_button)


        start.setOnClickListener {
            val intent = Intent(this@MainActivity, WorkActivity::class.java)
            startActivity(intent)
            finish()
        }

//        settings.setOnClickListener {
//            val intent = Intent(this@MainActivity, SettingsActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

        log_out_button.setOnClickListener {
            val intent = Intent(this@MainActivity, enterActivity::class.java)
            startActivity(intent)
            finish()
        }



    }


}