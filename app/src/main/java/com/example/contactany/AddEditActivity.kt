package com.example.contactany

import android.app.Activity
import android.app.Dialog
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.contactany.databinding.ActivityAddEditBinding
import com.example.contactany.roomdb.DbBuilder
import com.example.contactany.roomdb.entity.Contact
import com.github.dhaval2404.imagepicker.ImagePicker
import es.dmoral.toasty.Toasty

class AddEditActivity : AppCompatActivity() {
    val binding  by lazy {
        ActivityAddEditBinding.inflate(layoutInflater)
    }
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
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // view profile picture
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
            // get data base
           val db =   DbBuilder.getdb(this )
            // then insert date in database
            val i = db?.ContactDao()?.createContact(contact)
            if (i != null) {
                if (i > 0 ){
                    Toasty.success(this, "Success!", Toast.LENGTH_SHORT, true).show();
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