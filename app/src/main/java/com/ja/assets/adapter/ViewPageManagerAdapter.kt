package com.ja.assets.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class ViewPageManagerAdapter(fragmentManager: FragmentManager, private val fragmentsList: ArrayList<Fragment>) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return fragmentsList[position]
    }

    override fun getCount(): Int {
        return fragmentsList.size
    }
}
