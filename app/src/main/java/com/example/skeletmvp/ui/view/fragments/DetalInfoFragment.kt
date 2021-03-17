package com.example.skeletmvp.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentDetalInfoBinding
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment

class DetalInfoFragment : BaseFragment<FragmentDetalInfoBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetalInfoBinding
        get() = FragmentDetalInfoBinding::inflate

    override fun setupViews() {

    }

}