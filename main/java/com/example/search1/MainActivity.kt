package com.example.search1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title="oo 도서관"

        Handler(Looper.getMainLooper()).postDelayed({
            var intent=Intent(this,SecondActivity::class.java)
            startActivity(intent)
        },3000)
    }
}



