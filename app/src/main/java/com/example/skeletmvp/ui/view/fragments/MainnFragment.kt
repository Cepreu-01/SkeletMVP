package com.example.skeletmvp.ui.view.fragments

import android.os.Bundle
import android.view.*
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentMainnBinding

class MainnFragment : BaseFragment<FragmentMainnBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainnBinding
        get() = FragmentMainnBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun setup() {

    }

}