package com.example.contactany

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.contactany.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var viewModel : TempViewModel
    val binding by lazy {
        ActivityMain2Binding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(TempViewModel::class.java)
        binding.textOfViewModel.text = viewModel.provideA().toString()
        binding.changeButton.setOnClickListener {
            viewModel.a++
            binding.textOfViewModel.text = viewModel.provideA().toString()
        }
    }
}