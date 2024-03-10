package com.example.contactany.roomdb.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import java.io.Serializable
import java.util.Date


@Entity

data class Contact (
    @PrimaryKey val id : Int ? = null,
    var profile : ByteArray ?  = null,
    var name : String?=null,
    var phoneNumber : String?=null,
    var email : String ? = null,
//    val date : Date
) : Serializable