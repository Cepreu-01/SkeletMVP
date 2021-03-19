package com.example.skeletmvp.ui.view.fragments.coordinator

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


        val login = getCurrentUserLogin()
        val bundle = bundleOf("new_args" to login)

        val reposFragment = ReposFragment()
        val savedRepoFragment = SavedRepoFragment()

        reposFragment.arguments=bundle

        pagerAdapter.addFragmentWithTitle(reposFragment,"Repos")
        pagerAdapter.addFragmentWithTitle(savedRepoFragment,"Saved")
        binding.viewPager.adapter = pagerAdapter

    }
    private fun getCurrentUserLogin():String?{
        val login=activity?.intent?.getStringExtra(USER_LOGIN)
        if (!login.isNullOrEmpty()){
            return login
        } else return null
    }

}