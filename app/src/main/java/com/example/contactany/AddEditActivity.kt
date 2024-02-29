package com.example.contactany

import android.app.Dialog
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.contactany.databinding.ActivityAddEditBinding
import com.example.contactany.roomdb.DbBuilder
import com.example.contactany.roomdb.entity.Contact
import es.dmoral.toasty.Toasty

class AddEditActivity : AppCompatActivity() {
    val binding  by lazy {
        ActivityAddEditBinding.inflate(layoutInflater)
    }
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // view profile picture
        binding.imageView.setOnClickListener {
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
        }
        binding.savebutton.setOnClickListener {
            var contact = Contact(name=binding.name.text.toString(),
                phoneNumber = binding.phonenumber.text.toString(),
                email = binding.gmailId.text.toString())
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
    }
}