package com.example.skeletmvp.repository.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserModel(
    @ColumnInfo(name = "user_id")
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name = "user_name")
    var name:String
)