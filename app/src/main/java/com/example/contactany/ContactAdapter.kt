package com.example.contactany

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactany.databinding.ContactItemBinding
import com.example.contactany.roomdb.entity.Contact


class ContactAdapter(var contactList : List<Contact> , var   context: Context) : RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {
    inner class MyViewHolder (val binding : ContactItemBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = ContactItemBinding.inflate(LayoutInflater.from(context) , parent , false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contact = contactList.get(position)
        if (contact.profile == null){
            var signname = contact.name
            var allword = ArrayList<String>()
            var tempword = ""
            if (signname != null)
                for (i in signname){
                    if (i == ' '){
                        allword.add(tempword)
                        tempword =""
                    }else{
                        tempword += i
                    }

                }
            if(tempword.length != 0)
                allword.add(tempword)
            var newSign = ""
            for (i in allword){
                newSign += i[0]
            }
            holder.binding.sign.text = newSign
        }else{
            var imageByte = contact.profile
            if(imageByte!=null){
                var image = BitmapFactory.decodeByteArray(imageByte , 0 , imageByte.size)
                holder.binding.profileImage.setImageBitmap(image)
                holder.binding.profileImage.visibility = View.VISIBLE
                holder.binding.sign.visibility = View.GONE
            }
            else{
                var signname = contact.name
                var allword = ArrayList<String>()
                var tempword = ""
                if (signname != null)
                    for (i in signname){
                        if (i == ' '){
                            allword.add(tempword)
                            tempword =""
                        }else{
                            tempword += i
                        }

                    }
                if(tempword.length != 0)
                    allword.add(tempword)
                var newSign = ""
                for (i in allword){
                    newSign += i[0]
                }
                holder.binding.sign.text = newSign
                holder.binding.profileImage.visibility = View.GONE
                holder.binding.sign.visibility = View.VISIBLE
            }
        }

        // If you have not an image then it will save as the first two letter for your name

        // completed the name part in image
        holder.binding.name.text = contact.name
        holder.binding.phone.text = contact.phoneNumber
        holder.binding.email.text = contact.email
    }
}