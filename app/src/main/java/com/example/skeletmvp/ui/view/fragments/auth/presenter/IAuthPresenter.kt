package com.example.skeletmvp.ui.view.fragments.auth.presenter

import android.app.Activity
import android.content.Context
import android.widget.EditText



interface IAuthPresenter {
    fun getLogin(edt_login: EditText,activity: Activity?):String?
    fun sendCurrentLoginToActivityArgs(currentLogin:String,activity:Activity?)
    fun saveCurrentLogin(currentLogin:String)
    fun initParams(context: Context)
}