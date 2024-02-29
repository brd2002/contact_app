package com.example.contactany

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactany.databinding.ActivityMainBinding
import com.example.contactany.roomdb.DbBuilder
import com.example.contactany.roomdb.entity.Contact

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    var contactlist = ArrayList<Contact>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this@MainActivity , AddEditActivity::class.java)
            startActivity(intent)
//            finish()
        }
        DbBuilder.getdb(this)?.ContactDao()?.let { contactlist.addAll( it.readContact()) }
        binding.rv.layoutManager = LinearLayoutManager(this  )
        binding.rv.adapter = ContactAdapter(contactlist , this)
    }
}