package com.example.skeletmvp.ui.view.fragments.detalinfo.presenter

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.skeletmvp.repository.retrofit.model.UserRepoPOJOItem

interface IDetalInfoPresenter {
    fun initArgs(context: Context)
    fun getLogin(activity: Activity?):String?
    fun setupInfo(
        login:String?,
        tv_login:TextView,
        tv_repo_name:TextView,
        tv_description:TextView,
        tv_forks_count:TextView,
        tv_stars_count:TextView,
        tv_created_at:TextView,
        avatar:ImageView,
        arguments: Bundle?
    )
    fun getRepoByPosition(arguments: Bundle?):Int?
    fun saveCurrentRepo()
    fun createBitMap(src:String?): Bitmap?
}