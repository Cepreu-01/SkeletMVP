package com.example.skeletmvp.ui.view.fragments.saved.presenter

import android.app.Activity
import android.content.Context
import android.widget.ArrayAdapter

interface ISavedRepoPresenter {
    fun removeRepo(repoName:String, adapter: ArrayAdapter<String>)
    fun removeAllRepos()
    fun getCurrentLogin(activity: Activity):String?
    fun initArgs(context: Context)
    fun observeSavedRepos(login:String?,adapter: ArrayAdapter<String>,arrayList:ArrayList<String>)

}