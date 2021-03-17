package com.example.skeletmvp.repository.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserLogin (
    @PrimaryKey
    val login:String
)