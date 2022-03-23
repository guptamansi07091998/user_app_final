package com.example.navigation

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class helper(context:Context) :SQLiteOpenHelper(context,"userDB",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table user_d (user_id integer primary key AUTOINCREMENT,name TEXT,number text,email_address text,address text)")
       // db?.execSQL("insert into user_d(name,number,email_address,address) values('mansi','7983071546','mansi@gmail.com','mmnbggtyh')")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}