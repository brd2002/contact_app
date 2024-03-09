package com.example.contactany

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactany.databinding.ActivityMainBinding
import com.example.contactany.mvvmarch.MainActivityViewModel
import com.example.contactany.roomdb.DbBuilder
import com.example.contactany.roomdb.entity.Contact

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    var contactList = ArrayList<Contact>()
    var viewModel : MainActivityViewModel ? = null
    lateinit var adapter: ContactAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (ContextCompat.checkSelfPermission(this  , Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            createUI()
        }else{
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.CALL_PHONE ) , DIAL_CALL_PERMISSION)
        }
    }
fun createUI(){
    viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
    binding.floatingActionButton.setOnClickListener {
        val intent = Intent(this@MainActivity , AddEditActivity::class.java)
        startActivity(intent)
//            finish()
    }
    viewModel!!.data.observeForever {
        contactList.clear()
        it.map {
            contactList.add(it)
        }
        adapter.notifyDataSetChanged()
    }
    ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        1 ,
        ItemTouchHelper.LEFT or ItemTouchHelper.DOWN or ItemTouchHelper.RIGHT
    ) {

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false;
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (ItemTouchHelper.LEFT == direction){
                viewModel!!.deletecontact(contactList.get(viewHolder.adapterPosition))
            }
            else{
                val intent = Intent(Intent.ACTION_CALL)
                intent.data = Uri.parse("tel:${contactList.get(viewHolder.adapterPosition).phoneNumber}")
                startActivity(intent)
                adapter.notifyDataSetChanged()
            }

        }

    }).attachToRecyclerView(binding.rv)
    binding.rv.layoutManager = LinearLayoutManager(this  )
    adapter = ContactAdapter(contactList , this)
    binding.rv.adapter = adapter
}
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode  == DIAL_CALL_PERMISSION){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                createUI()
            }else {
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.CALL_PHONE ) , DIAL_CALL_PERMISSION)
            }
        }
    }
}