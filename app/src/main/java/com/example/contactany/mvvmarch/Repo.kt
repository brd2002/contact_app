package com.example.contactany.mvvmarch

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.contactany.dbName
import com.example.contactany.roomdb.Database

import com.example.contactany.roomdb.DbBuilder
import com.example.contactany.roomdb.entity.Contact


class Repo(var context : Context ) {
    var database : Database ? = null
    init {
        database = DbBuilder.getdb(context)
    }
    fun getData() : LiveData< List<Contact>>?{
      return database?.ContactDao()?.readContact()
    }
    fun insertData(contact : Contact): Long?{
       return  database?.ContactDao()?.createContact(contact)
    }
    fun deleteData (contact: Contact){
        database?.ContactDao()?.deleteContact(contact)
    }
    fun updateData (contact: Contact){
        database?.ContactDao()?.updateContact(contact)
    }

}