package com.example.contactany.roomdb.entity

import androidx.room.Entity
import java.util.Date


@Entity
class Contact (
    val id : Int ? ,
    val profile : Int ? ,
    val name : String ,
    val phoneNumber : String ,
    val email : String ? ,
//    val date : Date
)