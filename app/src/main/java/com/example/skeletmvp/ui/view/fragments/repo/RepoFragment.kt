package com.example.skeletmvp.ui.view.fragments.repo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentRepoBinding
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment

class RepoFragment : BaseFragment<FragmentRepoBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRepoBinding
        get() = FragmentRepoBinding::inflate

    override fun setupViews() {

    }

}