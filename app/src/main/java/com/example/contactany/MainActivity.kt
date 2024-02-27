package com.example.contactany

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contactany.roomdb.DbBuilder
import com.example.contactany.roomdb.entity.Contact

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DbBuilder.getdb(this )?.
        ContactDao()?.
        createContact(
            Contact(name = "Bharat Ruidas" , phoneNumber = "1234567890" ,)
        )
    }
}