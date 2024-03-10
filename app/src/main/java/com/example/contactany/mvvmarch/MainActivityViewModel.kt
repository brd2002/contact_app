package com.example.contactany.mvvmarch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.contactany.roomdb.Database
import com.example.contactany.roomdb.DbBuilder
import com.example.contactany.roomdb.entity.Contact

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

      var repo : Repo
     lateinit var data :LiveData<List<Contact>>

    init {
        repo = Repo(application)

         data = repo.getData()!!
    }
    fun deletecontact (contact: Contact){
        repo.deleteData(contact)
    }
}