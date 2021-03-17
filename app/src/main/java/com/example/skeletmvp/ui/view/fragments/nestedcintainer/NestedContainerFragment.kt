package com.example.skeletmvp.ui.view.fragments.nestedcintainer

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.skeletmvp.databinding.FragmentNestedContainerBinding
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment

class NestedContainerFragment : BaseFragment<FragmentNestedContainerBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentNestedContainerBinding
        get() = FragmentNestedContainerBinding::inflate

    override fun setupViews() {

    }

}