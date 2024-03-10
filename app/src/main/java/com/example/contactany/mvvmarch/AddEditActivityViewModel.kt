package com.example.contactany.mvvmarch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.contactany.roomdb.Database
import com.example.contactany.roomdb.DbBuilder
import com.example.contactany.roomdb.entity.Contact

class AddEditActivityViewModel(application: Application) : AndroidViewModel(application) {
    var repo :Repo
    init {
       repo = Repo(application)

    }
    fun storeData (contact: Contact , function :(i : Long?)-> Unit){
       var i =  repo.insertData(contact)
        function(i)
    }
}