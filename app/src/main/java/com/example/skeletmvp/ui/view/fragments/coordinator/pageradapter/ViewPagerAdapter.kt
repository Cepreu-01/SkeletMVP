package com.example.skeletmvp.ui.view.fragments.coordinator.pageradapter

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

class ViewPagerAdapter(cfm:FragmentManager,b:Int): FragmentStatePagerAdapter(cfm,b) {
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