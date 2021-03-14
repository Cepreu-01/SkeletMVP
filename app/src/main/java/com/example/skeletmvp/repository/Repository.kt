package com.example.skeletmvp.repository

import android.content.Context
import com.example.skeletmvp.repository.room.db.UserDatabase
import com.example.skeletmvp.repository.room.model.UserAddress
import com.example.skeletmvp.repository.room.model.UserModel

class Repository(context: Context) {
    val dao = UserDatabase.getInstance(context)
    var INSTANCE = UserDatabase.removeInstance()

    fun getUserWithAddress() = dao?.getDao()?.getUserWithAddress()
    fun insertUserWithAddress(userModel: UserModel,userAddress: UserAddress) = dao?.getDao()?.insertUserWithAddress(userModel,userAddress)
    fun updateUserWithAddress(userModel: UserModel,userAddress: UserAddress) = dao?.getDao()?.updateUserWithAddress(userModel,userAddress)
    fun deleteUser(id:Int) = dao?.getDao()?.deleteUser(id)
    fun closeDB(){
        INSTANCE
    }
}