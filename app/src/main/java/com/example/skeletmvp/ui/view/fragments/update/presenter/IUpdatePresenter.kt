package com.example.skeletmvp.ui.view.fragments.update.presenter

import android.os.Bundle
import com.example.skeletmvp.databinding.FragmentUpdateBinding

interface IUpdatePresenter {
    fun setUserInfo(arguments: Bundle?,binding: FragmentUpdateBinding)
    fun getUserInfo(binding: FragmentUpdateBinding):Bundle?
    fun createUserToUpdate(user_id:Int,user_name:String,address_id:Int,address:String):Bundle
}