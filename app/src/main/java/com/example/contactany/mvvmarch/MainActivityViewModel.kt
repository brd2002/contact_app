package com.example.contactany.mvvmarch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.contactany.roomdb.Database
import com.example.contactany.roomdb.DbBuilder
import com.example.contactany.roomdb.entity.Contact

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

     private var repo : Repo
     var contact = ArrayList<Contact>()
    init {
        repo = Repo(application)

        repo.getData()?.map {
            contact.add(it)
        }
    }
}