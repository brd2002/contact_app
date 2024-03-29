package com.example.contactany.roomdb.dao

import androidx.lifecycle.LiveData
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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createContact(contact:Contact) : Long
    // update
    @Update
    suspend fun updateContact(contact: Contact)
    // read
    @Query("SELECT * FROM Contact")
    fun readContact() : LiveData<List<Contact> >
    // delete
    @Delete
    suspend fun deleteContact(contact: Contact)
}