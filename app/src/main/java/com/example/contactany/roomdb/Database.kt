package com.example.contactany.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactany.roomdb.dao.ContactDao
import com.example.contactany.roomdb.entity.Contact

@Database(entities = [Contact::class], version = 2 , exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract  fun ContactDao() : ContactDao
}