
package com.example.myapplication

import DBHelper
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.*
import com.google.android.gms.common.ConnectionResult.TIMEOUT
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.NonCancellable.cancel
import java.util.*
import kotlin.random.Random

class BreakActivity : AppCompatActivity() {

    lateinit var timer: CountDownTimer

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_break)
        val stop_button = findViewById<Button>(R.id.stop_button)
        val break_time = findViewById<TextView>(R.id.break_text)
        break_time.text = "Break"
        val imageView = findViewById<ImageView>(R.id.imageView)

        val images: IntArray = intArrayOf(
                    R.drawable.p1,
                    R.drawable.p2,
                    R.drawable.p3,
                    R.drawable.p4
        )
        val random = Random
        imageView.setImageResource(images[random.nextInt(images.size)])

        stop_button.setOnClickListener {
            //task.cancel()
            val intent = Intent(this@BreakActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        var minutes = 4
        var counter = 0
        var countTime = findViewById<TextView>(R.id.time_text)

        timer = object : CountDownTimer((minutes*1000).toLong(), 1000) {       //*60
            override fun onTick(millisUntilFinished: Long) {
                countTime.text = counter.toString()
                counter++
            }
            override fun onFinish() {
                //countTime.text = "Finished"
                val intent = Intent(this@BreakActivity, WorkActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        timer.start()


        //RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        //android:id="@+id/root_layout

//        var root_layout = findViewById<RelativeLayout>(R.id.layout)
//        val animDrawable = root_layout.background as AnimationDrawable
//        animDrawable.setEnterFadeDuration(10)
//        animDrawable.setEnterFadeDuration(500)
//        animDrawable.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

}



