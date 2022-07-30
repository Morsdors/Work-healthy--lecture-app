package com.example.myapplication

//import kotlinx.android.synthetic.main.*     //content_main.

import DBHelper
import DBHelper.Companion.TABLE_NAME
//import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.view.Gravity
import android.view.Gravity.CENTER_HORIZONTAL


class LoginActivity : AppCompatActivity() {

    private lateinit var handler:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        //Toast.makeText(this, "11111do logowania", Toast.LENGTH_SHORT).show()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()
        val log_in = findViewById<Button>(R.id.login_button)
        val login_name = findViewById<EditText>(R.id.login_name)
        val login_password = findViewById<EditText>(R.id.login_password)
        val back = findViewById<Button>(R.id.back)
        handler = DBHelper(this)


        log_in.setOnClickListener {
            if(handler.userPresent(login_name.text.toString(), login_password.text.toString())){

    //            //handler.currentUserId = "SELECT handler.COL_ID FROM ${TABLE_NAME} WHERE handler.COL_NAME = login_name.text.toString() and handler.COL_PASSWORD = login_password.text.toString()"
                //handler.setCurrentId(login_name.text.toString(), login_password.text.toString())

                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
                Toast.makeText(this, handler.currentUserId, Toast.LENGTH_LONG).show()
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
            }else{
                //toast.setGravity(Gravity.TOP or Gravity.LEFT, 0, 0)
                /*val toast = Toast.makeText(applicationContext,"User name or password is incorrect", Toast.LENGTH_SHORT)
                toast.setGravity(CENTER_HORIZONTAL, 100, 100)
                toast.show()*/
                Toast.makeText(this, "User name or password is incorrect", Toast.LENGTH_LONG).show()
            }
        }

        back.setOnClickListener {
            val intent = Intent(this@LoginActivity, enterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
