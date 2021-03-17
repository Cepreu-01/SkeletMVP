package com.example.skeletmvp.repository.room.dao

import androidx.room.*
import com.example.skeletmvp.repository.retrofit.model.User
import com.example.skeletmvp.repository.room.model.UserLogin
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveCurrentLogin(userLogin:UserLogin):Completable
    @Query("SELECT * FROM UserLogin")
    fun getCurrentLogin(): Maybe<UserLogin>

}