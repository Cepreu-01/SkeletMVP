package com.example.skeletmvp.ui.view.fragments.coordinator

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.skeletmvp.databinding.FragmentCoordinatorBinding
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import com.example.skeletmvp.ui.view.fragments.coordinator.pageradapter.ViewPagerAdapter
import com.example.skeletmvp.ui.view.fragments.info.InfoFragment
import com.example.skeletmvp.ui.view.fragments.repo.RepoFragment


class CoordinatorFragment : BaseFragment<FragmentCoordinatorBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCoordinatorBinding
        get() = FragmentCoordinatorBinding::inflate

    override fun setupViews() {
        val pagerAdapter = ViewPagerAdapter(requireActivity().supportFragmentManager,0)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        pagerAdapter.addFragmentWithTitle(InfoFragment(),"Info")
        pagerAdapter.addFragmentWithTitle(RepoFragment(),"Repo")
        binding.viewPager.adapter = pagerAdapter

    }

}