package com.mkamel.startup

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*


class MainActivity2 : AppCompatActivity() {

    lateinit var db: DataBaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        db = DataBaseHandler(applicationContext)

        doAll()
    }

    fun doAll() {

        val childCount = table.childCount

        if (childCount > 1) {
            table.removeViews(1, childCount - 1)
        }

        var row = TableRow(this)

        val a1 = addTextView("")

        a1.isEnabled = false

        row.addView(a1)

        val a2 = addTextView("")

        a2.isEnabled = false

        row.addView(a2)

        val a4 = addTextView(getString(R.string.xcsss13))

        a4.isEnabled = false

        row.addView(a4)

        val a5 = addTextView(getString(R.string.xcsss12))

        a5.isEnabled = false

        row.addView(a5)

        val a6 = addTextView(getString(R.string.xcsss11))

        a6.isEnabled = false

        row.addView(a6)

        table.addView(row)

        var all: MutableList<User> = db.getAllUsers()
        for (i in 0 until all.size) {
            addRow(all[i])
        }


    }

    fun addRow(user: User) {

        var row = TableRow(this)

        val currntYear = Calendar.getInstance().get(Calendar.YEAR)

        val age0 = currntYear - user.age

        val b1 = addButton(getString(R.string.xcsss16), row)

        b1.setOnClickListener {
            /*
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("ID_UPDATE", ((it as Button).tag as User).id.toString())
            startActivity(intent)
            */
            if (!((it.tag as TableRow).getChildAt(4) as EditText).text.isNullOrBlank() && !((it.tag as TableRow).getChildAt(3) as EditText).text.isNullOrBlank()) {
                val a = ((it.tag as TableRow).getChildAt(3) as EditText).text.toString().toInt()
                val currntYear = Calendar.getInstance().get(Calendar.YEAR)
                val age = currntYear - a
                if (age > 0) {

                    var user = User(
                        ((it.tag as TableRow).tag as User).id,
                        ((it.tag as TableRow).getChildAt(4) as EditText).text.toString(),
                        ((it.tag as TableRow).getChildAt(3) as EditText).text.toString().toInt()
                    )

                    db.updateUser(user)

                    ((it.tag as TableRow).getChildAt(2) as EditText).setText(age.toString())

                } else {
                    showAlertDialog(getString(R.string.xcsss8) + " " + currntYear)
                }
            }else
            {
                showAlertDialog(getString(R.string.xcsss2))
            }

        }

        val b2 = addButton(getString(R.string.xcsss17), row)

        b2.setOnClickListener {

            getAlertDialog(
                getString(R.string.xcsss19),
                ((it.tag as TableRow).tag as User).id.toString()
            )


        }

        row.setTag(user)

        row.addView(b1)

        row.addView(b2)

        val ff = addTextView(age0.toString())

        ff.isEnabled = false

        row.addView(ff)

        val ss: EditText = addTextView(user.age.toString())

        ss.inputType = InputType.TYPE_CLASS_NUMBER

        val maxLength = 4
        val FilterArray = arrayOfNulls<InputFilter>(1)
        FilterArray[0] = LengthFilter(maxLength)
        ss.setFilters(FilterArray)

        row.addView(ss)

        val hh = addTextView(user.name)

        hh.inputType = InputType.TYPE_CLASS_TEXT

        val maxLength1 = 30
        val FilterArray1 = arrayOfNulls<InputFilter>(1)
        FilterArray1[0] = LengthFilter(maxLength1)
        hh.setFilters(FilterArray1)

        row.addView(hh)

        table.addView(row)
    }

    private fun showAlertDialog(msg: String) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity2)
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


    private fun getAlertDialog(msg: String, ids: String) {


        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity2)
        alertDialog.setTitle(R.string.xcsss1)
        alertDialog.setCancelable(false)
        alertDialog.setMessage(msg)
        alertDialog.setPositiveButton("yes") { _, _ ->
            val kk = msg
            val ff = ids
            db.deleteUserByID(ff.toInt())

            doAll()

        }
        alertDialog.setNegativeButton("No") { _, _ ->

        }

        //performing positive action
        //alertDialog.setPositiveButton("Yes"){dialogInterface, which ->
        //    Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()
        //}
        //performing cancel action
        //alertDialog.setNeutralButton(R.string.xcsss3) { dialogInterface, which ->
        //    Toast.makeText(applicationContext, R.string.xcsss4, Toast.LENGTH_LONG).show()
        //}
        //performing negative action
        //alertDialog.setNegativeButton("No"){dialogInterface, which ->
        //    Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
        // }
        // Create the AlertDialog

        alertDialog.show()

    }


    fun addTextView(tt: String): EditText {

        var v = EditText(this)

        v.gravity = Gravity.CENTER

        v.setPadding(10, 10, 10, 10)

        v.setText(tt)

        v.textSize = 22.toFloat()

        return v
    }

    fun addButton(tt: String, tb: TableRow): Button {
        var v = Button(this)

        v.text = tt

        v.setTag(tb)

        v.setPadding(5, 5, 5, 5)

        return v
    }


    fun on_click(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}