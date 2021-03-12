package com.example.skeletmvp.ui.view.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentMainnBinding

class MainnFragment : BaseFragment<FragmentMainnBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainnBinding
        get() = FragmentMainnBinding::inflate

    override fun setup() {
        binding.button2?.setOnClickListener {
            findNavController().navigate(R.id.action_mainnFragment_to_addFragment)
        }
    }
}