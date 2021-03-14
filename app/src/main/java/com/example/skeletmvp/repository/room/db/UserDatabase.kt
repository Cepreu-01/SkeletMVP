package com.example.skeletmvp.repository.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.skeletmvp.repository.room.dao.UserDao
import com.example.skeletmvp.repository.room.model.UserAddress
import com.example.skeletmvp.repository.room.model.UserModel

@Database(entities = [UserModel::class,UserAddress::class], version = 1, exportSchema = true)
abstract class UserDatabase:RoomDatabase() {
    abstract fun getDao():UserDao
    companion object{
        var INSTANCE:UserDatabase?=null
        fun getInstance(context: Context):UserDatabase?{
            val tempInstance = INSTANCE
            return if(tempInstance!=null){
                tempInstance
            } else {
                val db = Room.databaseBuilder(
                    context,
                    UserDatabase::class.java,
                    "db"
                ).build()
                INSTANCE = db
                INSTANCE
            }
        }
    }
}