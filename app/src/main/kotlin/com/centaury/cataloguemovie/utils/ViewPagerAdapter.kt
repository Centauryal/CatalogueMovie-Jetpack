package com.centaury.cataloguemovie.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by Centaury on 10/7/2019.
 */
class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val fragmentList: MutableList<Fragment> = ArrayList()

    fun addFragment(fragment: Fragment) = fragmentList.add(fragment)

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}