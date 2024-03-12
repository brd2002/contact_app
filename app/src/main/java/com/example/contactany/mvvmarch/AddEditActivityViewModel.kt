package com.example.contactany.mvvmarch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactany.roomdb.Database
import com.example.contactany.roomdb.DbBuilder
import com.example.contactany.roomdb.entity.Contact
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddEditActivityViewModel(application: Application) : AndroidViewModel(application) {
    var repo :Repo
    init {
       repo = Repo(application)

    }
    fun storeData (contact: Contact , function :(i : Long?)-> Unit){
        viewModelScope.launch {
            var i =  repo.insertData(contact)
            function(i)
        }

    }
}