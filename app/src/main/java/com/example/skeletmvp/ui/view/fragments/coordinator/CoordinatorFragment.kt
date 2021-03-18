package com.example.skeletmvp.ui.view.fragments.coordinator

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.skeletmvp.databinding.FragmentCoordinatorBinding
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import com.example.skeletmvp.ui.view.fragments.coordinator.pageradapter.ViewPagerAdapter
import com.example.skeletmvp.ui.view.fragments.repos.ReposFragment
import com.example.skeletmvp.ui.view.fragments.saved.SavedRepoFragment
import com.example.skeletmvp.utils.USER_LOGIN


class CoordinatorFragment : BaseFragment<FragmentCoordinatorBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCoordinatorBinding
        get() = FragmentCoordinatorBinding::inflate

    override fun setupViews() {
        val pagerAdapter = ViewPagerAdapter(childFragmentManager,0)
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        val reposFragment = ReposFragment()
        val args = activity?.intent?.getStringExtra(USER_LOGIN)
        Log.e("ARGS",args.toString())
        val budnle = bundleOf("new_args" to args)
        reposFragment.arguments=budnle
        val savedRepoFragment = SavedRepoFragment()

        pagerAdapter.addFragmentWithTitle(reposFragment,"Repos")
        pagerAdapter.addFragmentWithTitle(savedRepoFragment,"Saved")
        binding.viewPager.adapter = pagerAdapter

    }

}