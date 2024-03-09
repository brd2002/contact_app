package com.example.contactany

import android.app.Activity
import android.app.Dialog
import android.graphics.BitmapFactory
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.text.set
import androidx.lifecycle.ViewModelProvider
import com.example.contactany.databinding.ActivityAddEditBinding
import com.example.contactany.mvvmarch.AddEditActivityViewModel
import com.example.contactany.roomdb.DbBuilder
import com.example.contactany.roomdb.entity.Contact
import com.github.dhaval2404.imagepicker.ImagePicker
import es.dmoral.toasty.Toasty

class AddEditActivity : AppCompatActivity() {
    val binding  by lazy {
        ActivityAddEditBinding.inflate(layoutInflater)
    }
    lateinit var viewModel: AddEditActivityViewModel
    var contact = Contact()
    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!
                binding.imageView.setImageURI(fileUri)
                val imageByte =   contentResolver.openInputStream(fileUri)?.readBytes()
                contact.profile = imageByte
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    var flags = -1
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(AddEditActivityViewModel::class.java)
        if (intent.hasExtra("FLAG")){
            flags = intent.getIntExtra("FLAG" , -1)
        }
        // view profile picture
        if (flags ==1 ) {

            if (Build.VERSION_CODES.TIRAMISU <= Build.VERSION.SDK_INT)
                contact = intent.getSerializableExtra("DATA", Contact::class.java)!!
            else {
                contact = intent.getSerializableExtra("DATA") as Contact
            }
            binding.savebutton.text = "Update Contact"
            var imageByte = contact.profile
            if (imageByte != null) {
                var image = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.size)
                binding.imageView.setImageBitmap(image)
            }
            binding.name.setText(contact.name)
            binding.gmailId.setText(contact.email)
            binding.phonenumber.setText(contact.phoneNumber)
            
        }
        binding.imageView.setOnLongClickListener {
            var dialog = Dialog(this )
            dialog.setContentView(R.layout.image_dialog)
            var image = dialog.findViewById<ImageView>(R.id.image)
            var imageObject = binding.imageView.drawable
            image.setImageDrawable(imageObject)
            dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent);
            val lp = WindowManager.LayoutParams()
            // this is the property of dialog window
            lp.width  = WindowManager.LayoutParams.WRAP_CONTENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            lp.blurBehindRadius = 50
            lp.flags = WindowManager.LayoutParams.FLAG_BLUR_BEHIND
            dialog.window?.attributes = lp
            dialog.show()
            true
        }
        binding.savebutton.setOnClickListener {
            contact.name  = binding.name.text.toString()
            contact.email = binding.gmailId.text.toString()
            contact.phoneNumber = binding.phonenumber.text.toString()
//            
            viewModel.storeData(contact){
                if (it != null) {
                    if (it > 0 ){
                        Toasty.success(this, "Success!", Toast.LENGTH_SHORT, true).show();
                    }
                }
            }

        }
        binding.imageView.setOnClickListener {
            ImagePicker.with(this)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }
    }
}