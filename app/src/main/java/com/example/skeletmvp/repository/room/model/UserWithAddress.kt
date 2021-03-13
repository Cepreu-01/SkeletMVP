package com.example.skeletmvp.repository.room.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserWithAddress(
    val user_id:Int,
    val user_name:String,
    val user_address_id:Int,
    val user_address:String
):Parcelable