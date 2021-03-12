package com.example.skeletmvp.ui.view.fragments.add.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.skeletmvp.databinding.FragmentAddBinding
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment

class AddFragment : BaseFragment<FragmentAddBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddBinding
        get() = FragmentAddBinding::inflate

    override fun setupViews() {

    }

}