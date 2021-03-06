package com.mkamel.startup

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.lang.Double

val DatabaseName = "MyDB"
val TableName = "Users"
val COL_NAME = "name"
val COL_AGE = "age"
val COL_ID = "id"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DatabaseName, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TableName + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_AGE + " INTEGER)"
        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun addUser(user: User) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, user.name)
        cv.put(COL_AGE, user.age)
        var result = db.insert(TableName, null, cv)
        /*if (result == -1.toLong()) {
            Toast.makeText(context, "Failed ", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success ", Toast.LENGTH_SHORT).show()
        }*/

        Toast.makeText(context, "User Added ", Toast.LENGTH_LONG).show()

        db.close()

    }

    fun getAllUsers(): MutableList<User> {
        var list: MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from $TableName ORDER BY id desc"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {

                var user = User()
                user.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.name = result.getString(result.getColumnIndex(COL_NAME))
                user.age = result.getString(result.getColumnIndex(COL_AGE)).toInt()
                list.add(user)

            } while (result.moveToNext())
        }
        result.close()
        db.close()

        return list
    }

    fun getUserById(id: String): User? {
        val user = User()
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM $TableName WHERE $COL_ID = ?"
        db.rawQuery(selectQuery, arrayOf(id)).use {
            if (it.moveToFirst()) {

                user.id = it.getInt(it.getColumnIndex(COL_ID))
                user.name = it.getString(it.getColumnIndex(COL_NAME))
                user.age = it.getInt(it.getColumnIndex(COL_AGE))

            }
        }

        db.close()
        return user
    }

    fun deleteUserByID(id: Int) {

        val db = this.writableDatabase

        db.delete(TableName, "$COL_ID=?", arrayOf(id.toString()))

        Toast.makeText(context, "Deleted User", Toast.LENGTH_LONG).show()

        db.close()
    }

    fun updateUser(user: User) {
        val db = this.writableDatabase

        var cv = ContentValues()
        cv.put(COL_NAME, user.name)
        cv.put(COL_AGE, user.age)

        db.update(TableName, cv, "$COL_ID=?", arrayOf(user.id.toString()))

        Toast.makeText(context, "Updated User", Toast.LENGTH_LONG).show()

        db.close()
    }
}