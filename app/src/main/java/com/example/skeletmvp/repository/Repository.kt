package com.example.skeletmvp.repository

import android.content.Context
import com.example.skeletmvp.repository.room.db.UserDatabase
import com.example.skeletmvp.repository.room.model.UserAddress
import com.example.skeletmvp.repository.room.model.UserModel

class Repository(context: Context) {
    val dao = UserDatabase.getInstance(context)?.getDao()

    fun getUserWithAddress() = dao?.getUserWithAddress()
    fun insertUserWithAddress(userModel: UserModel,userAddress: UserAddress) = dao?.insertUserWithAddress(userModel,userAddress)
    fun updateUserWithAddress(userModel: UserModel,userAddress: UserAddress) = dao?.updateUserWithAddress(userModel,userAddress)
    fun deleteUser(id:Int) = dao?.deleteUser(id)
}