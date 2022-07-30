package com.example.myapplication

import DBHelper
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class RankingActivity : AppCompatActivity() {

    private lateinit var handler: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
        val listView = findViewById<ListView>(R.id.usersList)
        val back = findViewById<Button>(R.id.backRanking)

        listView.adapter = MyCustomAdapter(this)


        handler = DBHelper(this)
        val ppl:List<DBHelper.Person> = handler.allUsers

        back.setOnClickListener {
            val intent = Intent(this@RankingActivity, enterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private class MyCustomAdapter(context: Context): BaseAdapter() { //BaseAdapter()  is the constructor for the class

        private lateinit var handler: DBHelper

        private val mContext: Context
        init{
            mContext = context
        }

        //responsible for how many rows in my list
        override fun getCount(): Int {
            handler = DBHelper(mContext)
            return handler.allUsers.size //5
        }
        //also ignore
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }
        //can ignore for now
        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.row_ranking, viewGroup, false)

            val positionTextView = rowMain.findViewById<TextView>(R.id.positionTextView)
            val pos = position+1
            positionTextView.text = "$pos. "

            handler = DBHelper(mContext)
            val nameTextView = rowMain.findViewById<TextView>(R.id.nameTextView)
            nameTextView.text = handler.allUsers.get(position).name
            //+handler.allUsers.get(position).points
            val pointsTextView = rowMain.findViewById<TextView>(R.id.pointsTextView)
            pointsTextView.text = handler.allUsers.get(position).points.toString()




            return rowMain
            //val textView = TextView(mContext)
            //textView.text = "Here is my ROW for my LISTVIEW"
            //return textView
        }
    }


}


