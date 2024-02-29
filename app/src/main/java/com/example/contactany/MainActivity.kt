package com.example.contactany

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contactany.databinding.ActivityMainBinding
import com.example.contactany.roomdb.DbBuilder
import com.example.contactany.roomdb.entity.Contact

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this@MainActivity , AddEditActivity::class.java)
            startActivity(intent)
//            finish()
        }
    }
}