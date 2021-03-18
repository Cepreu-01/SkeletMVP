package com.example.skeletmvp.repository.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.skeletmvp.repository.retrofit.model.UserRepoPOJOItem
import com.example.skeletmvp.repository.room.dao.UserDao
import com.example.skeletmvp.repository.room.model.UserLogin

@Database(entities = [UserLogin::class,UserRepoPOJOItem::class], version = 1, exportSchema = false)
abstract class UserDatabase:RoomDatabase() {
    abstract fun getDao():UserDao
    companion object{
        private const val DB_NAME = "Database"
        private var INSTANCE:UserDatabase?=null

        fun getInstance(context: Context):UserDatabase?{
            val tempInstance = INSTANCE
            return if(tempInstance!=null){
                tempInstance
            } else {
                val db = Room.databaseBuilder(
                    context,
                    UserDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                INSTANCE
            }
        }
        fun removeInstance(){
            INSTANCE?.close()
            INSTANCE = null
        }
    }
}