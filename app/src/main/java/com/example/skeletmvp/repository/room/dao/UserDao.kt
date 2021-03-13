package com.example.skeletmvp.repository.room.dao

import androidx.room.*
import com.example.skeletmvp.repository.room.model.UserAddress
import com.example.skeletmvp.repository.room.model.UserModel
import com.example.skeletmvp.repository.room.model.UserWithAddress
import io.reactivex.Observable

@Dao
interface UserDao {
    @Query("SELECT User.user_id AS user_id, User.user_name AS user_name, User_Address.address_id AS user_address_id, User_Address.address AS user_address FROM User, User_Address WHERE User.user_id == User_Address.address_id")
    fun getUserWithAddress():Observable<List<UserWithAddress>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertUser(userModel: UserModel)
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertUserAddress(userAddress: UserAddress)
    @Transaction
    fun insertUserWithAddress(userModel: UserModel,userAddress: UserAddress){
        insertUser(userModel)
        insertUserAddress(userAddress)
    }

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateUser(userModel: UserModel)
    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateUserAddress(userAddress: UserAddress)
    @Transaction
    fun updateUserWithAddress(userModel: UserModel,userAddress: UserAddress){
        updateUser(userModel)
        updateUserAddress(userAddress)
    }
    @Query("DELETE FROM User WHERE user_id = :id")
    fun deleteUser(id:Int)

}