package com.example.skeletmvp.ui.view.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.skeletmvp.databinding.FragmentMainnBinding

class MainnFragment : BaseFragment<FragmentMainnBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainnBinding
        get() = FragmentMainnBinding::inflate

    override fun setup() {

    }
}