package com.example.skeletmvp.ui.view.fragments.coordinator.pageradapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

class ViewPagerAdapter(fm:FragmentManager,b:Int):FragmentPagerAdapter(fm,b) {
    private val listOfFragments = ArrayList<Fragment>()
    private val listOfTitles = ArrayList<String>()

    private fun addFragment(fragment:Fragment){
        listOfFragments.add(fragment)
    }

    private fun addTitle(title:String){
        listOfTitles.add(title)
    }

    fun addFragmentWithTitle(fragment: Fragment,title: String){
        addFragment(fragment)
        addTitle(title)
    }

    override fun getItem(position: Int) = listOfFragments[position]

    override fun getCount() = listOfFragments.size

    override fun getPageTitle(position: Int) = listOfTitles[position]
}