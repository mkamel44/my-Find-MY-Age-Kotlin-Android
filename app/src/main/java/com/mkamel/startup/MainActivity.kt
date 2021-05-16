package com.mkamel.startup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

//الفيديو
//https://www.youtube.com/playlist?list=PLF8OvnCBlEY2w-zdVPozupapiKzLzpyUZ
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

    fun on_click(view: View) {
        if (!yearOfBirth.text.isNullOrBlank()) {
            val a = yearOfBirth.text.toString().toInt()
            val currntYear = Calendar.getInstance().get(Calendar.YEAR)
            val age = currntYear - a
            yourAge.text = getString(R.string.xcsss5) + " " + age + " " + getString(R.string.xcsss6)
        } else {
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle(R.string.xcsss1)

        alertDialog.setMessage(R.string.xcsss2)
        //alertDialog.setPositiveButton("yes") { _, _ -> Toast.makeText(this@MainActivity, "Alert dialog closed.", Toast.LENGTH_LONG).show()  }
        //alertDialog.setNegativeButton("No") { _, _ -> }

        //performing positive action
        //alertDialog.setPositiveButton("Yes"){dialogInterface, which ->
        //    Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()
        //}
        //performing cancel action
        alertDialog.setNeutralButton(R.string.xcsss3) { dialogInterface, which ->
            Toast.makeText(applicationContext, R.string.xcsss4, Toast.LENGTH_LONG).show()
        }
        //performing negative action
        //alertDialog.setNegativeButton("No"){dialogInterface, which ->
        //    Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
        // }
        // Create the AlertDialog

        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }
}