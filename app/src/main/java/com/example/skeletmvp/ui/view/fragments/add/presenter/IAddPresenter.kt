package com.example.skeletmvp.ui.view.fragments.add.presenter

import android.os.Bundle
import com.example.skeletmvp.databinding.FragmentAddBinding

interface IAddPresenter {
    fun createUser(binding: FragmentAddBinding): Bundle?
}