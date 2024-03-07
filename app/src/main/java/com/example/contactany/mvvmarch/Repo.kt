package com.example.contactany.mvvmarch

import android.content.Context
import com.example.contactany.dbName
import com.example.contactany.roomdb.Database

import com.example.contactany.roomdb.DbBuilder
import com.example.contactany.roomdb.entity.Contact


class Repo(var context : Context ) {
    var database : Database ? = null
    init {
        database = DbBuilder.getdb(context)
    }
    fun getData() : List<Contact> ?{
      return database?.ContactDao()?.readContact()
    }
    fun insertData(contact : Contact){
        database?.ContactDao()?.createContact(contact)
    }
    fun deleteData (contact: Contact){
        database?.ContactDao()?.deleteContact(contact)
    }
    fun updateData (contact: Contact){
        database?.ContactDao()?.updateContact(contact)
    }

}