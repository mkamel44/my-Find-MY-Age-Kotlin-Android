package com.mkamel.startup

import android.content.Intent
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

    lateinit var db: DataBaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DataBaseHandler(applicationContext)

        /*
         ID_UPDATE = getIntent().getStringExtra("ID_UPDATE").toString();

        if (ID_UPDATE != "null") {
            val user = db.getUserById(ID_UPDATE)
            val currntYear = Calendar.getInstance().get(Calendar.YEAR)
            val age = currntYear - user!!.age
            personName.setText(user.name)
            yearOfBirth.setText(user.age.toString())
            yourAge.text = getString(R.string.xcsss5) + " " + age + " " + getString(R.string.xcsss6)
            yourAge.tag = user.id.toString()
            btn2.text = getString(R.string.xcsss18)
        }
         */
    }

    fun on_click(view: View) {
        if (!yearOfBirth.text.isNullOrBlank() && !personName.text.isNullOrBlank()) {
            val a = yearOfBirth.text.toString().toInt()
            val currntYear = Calendar.getInstance().get(Calendar.YEAR)
            val age = currntYear - a
            if (age > 0) {
                yourAge.text =
                    getString(R.string.xcsss5) + " " + age + " " + getString(R.string.xcsss6)
                var user = User(personName.text.toString(), yearOfBirth.text.toString().toInt())

                db.addUser(user)

            } else {
                showAlertDialog(getString(R.string.xcsss8) + " " + currntYear)
            }

        } else {
            showAlertDialog(getString(R.string.xcsss2))
        }
    }

    private fun showAlertDialog(msg: String) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle(R.string.xcsss1)

        alertDialog.setMessage(msg)
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

    fun on_click1(view: View) {

        if (db.getAllUsers().size > 0) {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)

        } else {
            showAlertDialog(getString(R.string.xcsss15))
        }


    }

}

