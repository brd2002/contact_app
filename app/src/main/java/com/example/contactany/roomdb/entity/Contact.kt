package com.example.contactany.roomdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity
class Contact (
   @PrimaryKey val id : Int ? = null,
    val profile : Int ?  = null,
    val name : String ,
    val phoneNumber : String ,
    val email : String ? = null ,
//    val date : Date
)