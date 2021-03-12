package com.example.skeletmvp.ui.view.fragments.main.view

import android.view.*
import com.example.skeletmvp.databinding.FragmentMainnBinding
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment

class MainnFragment : BaseFragment<FragmentMainnBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainnBinding
        get() = FragmentMainnBinding::inflate

    override fun setupViews() {

    }

}