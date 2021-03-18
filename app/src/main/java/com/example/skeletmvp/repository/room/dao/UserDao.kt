package com.example.skeletmvp.repository.room.dao

import androidx.room.*
import com.example.skeletmvp.repository.retrofit.model.Repo
import com.example.skeletmvp.repository.retrofit.model.User
import com.example.skeletmvp.repository.retrofit.model.UserRepoPOJOItem
import com.example.skeletmvp.repository.room.model.UserLogin
import io.reactivex.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveCurrentLogin(userLogin:UserLogin):Completable
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveCurrentRepo(repo:UserRepoPOJOItem):Completable
    @Query("SELECT * FROM UserLogin")
    fun getCurrentLogin(): Maybe<UserLogin>
    @Query("SELECT * FROM UserRepoPOJOItem WHERE UserRepoPOJOItem.Ownerlogin ==:currentLogin")
    fun getSavedRepos(currentLogin:String):Observable<List<UserRepoPOJOItem>>
    @Query("DELETE FROM UserRepoPOJOItem WHERE UserRepoPOJOItem.name ==:repoName")
    fun removeRepo(repoName:String):Completable

}