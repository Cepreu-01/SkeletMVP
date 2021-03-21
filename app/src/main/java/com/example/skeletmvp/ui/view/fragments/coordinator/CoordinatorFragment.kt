package com.example.skeletmvp.ui.view.fragments.coordinator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.example.skeletmvp.databinding.FragmentCoordinatorBinding
import com.example.skeletmvp.repository.Repository
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import com.example.skeletmvp.ui.view.fragments.coordinator.pageradapter.ViewPagerAdapter
import com.example.skeletmvp.ui.view.fragments.repos.view.ReposFragment
import com.example.skeletmvp.ui.view.fragments.saved.view.SavedRepoFragment
import com.example.skeletmvp.utils.USER_LOGIN


class CoordinatorFragment : BaseFragment<FragmentCoordinatorBinding>() {
    private var repository:Repository?=null
    private var viewPagerAdapter:ViewPagerAdapter?=null
    private var viewPager:ViewPager?=null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCoordinatorBinding
        get() = FragmentCoordinatorBinding::inflate

    override fun setupViews() {
        repository = Repository(requireContext())

        //val pagerAdapter = ViewPagerAdapter(childFragmentManager,0)
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager,0)

        val reposFragment =
            ReposFragment()
        val savedRepoFragment =
            SavedRepoFragment()



        //pagerAdapter.addFragmentWithTitle(reposFragment,"Repos")
        //pagerAdapter.addFragmentWithTitle(savedRepoFragment,"Saved")

        viewPagerAdapter?.addFragmentWithTitle(reposFragment,"Repos")
        viewPagerAdapter?.addFragmentWithTitle(savedRepoFragment,"Saved")

        //val viewPager = binding.viewPager
        viewPager = binding.viewPager
        //viewPager.adapter = pagerAdapter
        viewPager?.adapter = viewPagerAdapter
        viewPager?.offscreenPageLimit=2
        binding.tabLayout.setupWithViewPager(viewPager)


    }

    override fun onStop() {
        super.onStop()
        repository?.closeDB()
        repository?.destroyRefs()

    }

}