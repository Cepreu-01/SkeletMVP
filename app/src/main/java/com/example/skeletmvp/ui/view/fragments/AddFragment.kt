package com.example.skeletmvp.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentAddBinding

class AddFragment : BaseFragment<FragmentAddBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddBinding
        get() = FragmentAddBinding::inflate

    override fun setup() {

    }

}