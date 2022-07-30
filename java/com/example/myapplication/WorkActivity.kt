
package com.example.myapplication


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import android.widget.*
import kotlin.concurrent.thread

class WorkActivity : AppCompatActivity() {

    lateinit var timer: CountDownTimer



    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)
        val stop_button = findViewById<Button>(R.id.stop_button)

        var root_layout = findViewById<RelativeLayout>(R.id.layout)
        val animDrawable = root_layout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(500)
        animDrawable.setExitFadeDuration(1000)
        animDrawable.start()

        Rotator()


        stop_button.setOnClickListener {
            //task.cancel()
            val intent = Intent(this@WorkActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        var minutes = 5
        var counter = 0
        var countTime = findViewById<TextView>(R.id.time_text)

        timer = object : CountDownTimer((minutes*1000).toLong(), 1000) {       //*60
            override fun onTick(millisUntilFinished: Long) {
                countTime.text = counter.toString()
                counter++
            }
            override fun onFinish() {
                //countTime.text = "Finished"

                val intent = Intent(this@WorkActivity, BreakActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        timer.start()


        //RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        //android:id="@+id/root_layout

//        var root_layout = findViewById<RelativeLayout>(R.id.layout)
//        val animDrawable = root_layout.background as AnimationDrawable
//        animDrawable.setEnterFadeDuration(500)
//        animDrawable.setExitFadeDuration(1000)
//        animDrawable.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
        //sensorManager.unregisterListener(this)
    }

    private lateinit var sensorManager: SensorManager

    var xspeed = 0
    var yspeed = 0

    private fun setupSensor() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        //?.also {
        //            sensorManager.registerListener(this, it,
        //                SensorManager.SENSOR_DELAY_FASTEST,
        //                SensorManager.SENSOR_DELAY_FASTEST)
        //        }

//        sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)?.also {
//            sensorManager.registerListener(
//                this, it,
//                SensorManager.SENSOR_DELAY_FASTEST
//            )
//        }
    }

    fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val sides = event.values[0]
            val upDown = event.values[1]

            Toast.makeText(applicationContext, "Keep working!", Toast.LENGTH_SHORT).show()
            yspeed = sides.toInt()
            xspeed = upDown.toInt()
        }

//        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
//            val bright = event.values[0]
//
//            doggy(bright.toInt())
//        }
    }

//    var rotx = 0f
//    var roty = 0f

    fun Rotator() {
        thread {
//            val img = findViewById<ImageView>(R.id.imageView)

            while (true){
                if(xspeed!=0 || yspeed!=0){  //or yspeed!=0
                    Toast.makeText(applicationContext, "Keep working!", Toast.LENGTH_SHORT).show()
                }
//                rotx -= xspeed
//                img.rotationX = rotx
//
//                roty += yspeed
//                img.rotationY= roty
//
//                Thread.sleep(20)
            }
        }
    }

}

//private fun SensorManager.registerListener(workActivity: WorkActivity, it: Sensor, sensorDelayFastest: Int) {
//
//}



