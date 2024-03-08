package com.example.contactany.mvvmarch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.contactany.roomdb.Database
import com.example.contactany.roomdb.DbBuilder
import com.example.contactany.roomdb.entity.Contact

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    var db : Database ? = null
    var conatactList = ArrayList<Contact>()
    init {
        db = DbBuilder.getdb(application)
       db?.ContactDao()?.readContact()?.forEach {
           conatactList.add(it)
       }
    }
}