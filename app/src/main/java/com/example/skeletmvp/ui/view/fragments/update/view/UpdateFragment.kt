package com.example.skeletmvp.ui.view.fragments.update.view


import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.skeletmvp.databinding.FragmentUpdateBinding
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment


class UpdateFragment : BaseFragment<FragmentUpdateBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUpdateBinding
        get() = FragmentUpdateBinding::inflate

    override fun setupViews() {

    }

}