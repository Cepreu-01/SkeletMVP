package com.example.skeletmvp.repository.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.skeletmvp.repository.room.model.UserAddress
import com.example.skeletmvp.repository.room.model.UserModel

@Database(entities = [UserModel::class,UserAddress::class], version = 1, exportSchema = true)
abstract class UserDatabase:RoomDatabase() {
}