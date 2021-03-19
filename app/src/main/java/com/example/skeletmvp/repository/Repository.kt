package com.example.skeletmvp.repository

import android.content.Context
import com.example.skeletmvp.repository.retrofit.SimpleRetrofit
import com.example.skeletmvp.repository.retrofit.model.UserRepoPOJOItem
import com.example.skeletmvp.repository.room.db.UserDatabase
import com.example.skeletmvp.repository.room.model.UserLogin

class Repository(context: Context) {
    private var api = SimpleRetrofit.api
    private var dao = UserDatabase.getInstance(context)
    private var INSTANCE = UserDatabase.removeInstance()

    fun saveCurrentLogin(userLogin:UserLogin) = dao?.getDao()?.saveCurrentLogin(userLogin)
    fun getCurrentLogin() = dao?.getDao()?.getCurrentLogin()
    fun saveCurrentRepo(repo: UserRepoPOJOItem) = dao?.getDao()?.saveCurrentRepo(repo)
    fun getSavedRepos(currentLogin:String) = dao?.getDao()?.getSavedRepos(currentLogin)
    fun removeRepo(repoName:String) = dao?.getDao()?.removeRepo(repoName)
    fun removeAllRepos() = dao?.getDao()?.removeAllRepos()

    fun getUserRepos(user:String) = api.getUserRepos(user)
    fun getUserInfo(user:String) = api.getUserInfo(user)
    fun getUserRepoPojo(user:String) = api.getUserRepoPOGO(user)

    fun closeDB(){
        dao?.close()
        INSTANCE
    }
    fun destroyRefs(){
        dao = null
        api = null
    }

}