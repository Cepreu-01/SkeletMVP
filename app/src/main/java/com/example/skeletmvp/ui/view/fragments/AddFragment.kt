package com.example.skeletmvp.ui.view.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.skeletmvp.databinding.FragmentAddBinding

class AddFragment : BaseFragment<FragmentAddBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddBinding
        get() = FragmentAddBinding::inflate

    override fun setup() {

    }
}