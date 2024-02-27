package com.example.contactany.roomdb.dao

import androidx.room.Dao
import com.example.contactany.roomdb.entity.Contact

@Dao
interface ContactDao {
    // create
    fun createContact(contact:Contact){

    }
    // update
    fun updateContact(contact: Contact){

    }
    // read
    fun readContact() : List<Contact>
    // delete
    fun deleteContact(contact: Contact){

    }
}