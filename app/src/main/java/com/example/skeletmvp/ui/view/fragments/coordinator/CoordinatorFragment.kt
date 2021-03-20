package com.example.skeletmvp.ui.view.fragments.coordinator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.skeletmvp.databinding.FragmentCoordinatorBinding
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import com.example.skeletmvp.ui.view.fragments.coordinator.pageradapter.ViewPagerAdapter
import com.example.skeletmvp.ui.view.fragments.repos.view.ReposFragment
import com.example.skeletmvp.ui.view.fragments.saved.view.SavedRepoFragment
import com.example.skeletmvp.utils.USER_LOGIN


class CoordinatorFragment : BaseFragment<FragmentCoordinatorBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCoordinatorBinding
        get() = FragmentCoordinatorBinding::inflate

    override fun setupViews() {


        val pagerAdapter = ViewPagerAdapter(childFragmentManager,0)
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        val reposFragment =
            ReposFragment()
        val savedRepoFragment =
            SavedRepoFragment()

        pagerAdapter.addFragmentWithTitle(reposFragment,"Repos")
        pagerAdapter.addFragmentWithTitle(savedRepoFragment,"Saved")
        binding.viewPager.adapter = pagerAdapter


    }

}