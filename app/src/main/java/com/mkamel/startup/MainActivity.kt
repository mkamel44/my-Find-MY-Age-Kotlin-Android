package com.mkamel.startup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*
        btn.setOnClickListener({
            val a = yearOfBirth.text.toString().toInt()
            val currntYear = Calendar.getInstance().get(Calendar.YEAR)
            val age = currntYear - a
            yourAge.text = "Your Age is " + age + " Year"
        })*/
    }

    fun on_click(view:View)
    {
        val a = yearOfBirth.text.toString().toInt()
        val currntYear = Calendar.getInstance().get(Calendar.YEAR)
        val age = currntYear - a
        yourAge.text = "Your Age is " + age + " Year"
    }
}