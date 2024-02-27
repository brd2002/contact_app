package com.example.contactany.roomdb

import android.content.Context
import androidx.room.Room
import com.example.contactany.dbName

object DbBuilder {
    private var database : Database ? = null
    fun getdb(context : Context) : Database ?{
        if (database == null){
            database = Room.databaseBuilder(context ,
                Database::class.java ,
                dbName).allowMainThreadQueries().build()
        }
        return database

    }
}