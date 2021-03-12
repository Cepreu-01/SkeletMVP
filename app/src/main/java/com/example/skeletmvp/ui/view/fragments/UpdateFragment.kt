package com.example.skeletmvp.ui.view.fragments


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.skeletmvp.databinding.FragmentUpdateBinding


class UpdateFragment : BaseFragment<FragmentUpdateBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUpdateBinding
        get() = FragmentUpdateBinding::inflate

    override fun setup() {

    }

}