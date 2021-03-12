package com.example.skeletmvp.repository.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "User_Address",
    foreignKeys = [
        ForeignKey(
            entity = UserModel::class,
            parentColumns = ["user_id"],
            childColumns = ["address_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = false
        )
    ]
)
data class UserAddress(
    @ColumnInfo(name = "address_id")
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name = "address")
    var address:String
)