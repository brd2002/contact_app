package com.example.contactany.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.contactany.roomdb.entity.Contact

@Dao
interface ContactDao {
    // create
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun createContact(contact:Contact)
    // update
    @Update
    fun updateContact(contact: Contact)
    // read
    @Query("SELECT * FROM Contact")
    fun readContact() : List<Contact>
    // delete
    @Delete
    fun deleteContact(contact: Contact)
}