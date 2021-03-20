package com.example.skeletmvp.ui.view.fragments.repos.presenter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter

interface IReposPresenter {
    fun observeRepos(login:String?,adapter:ArrayAdapter<String>,arrayList:ArrayList<String>)
    fun getCurrentLogin(activity: Activity):String?
    fun initArgs(context: Context)
}