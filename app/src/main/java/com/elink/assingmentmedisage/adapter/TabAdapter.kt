package com.elink.assingmentmedisage.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
/**
 * Created by Nitin Sabale
 * @since 27-07-2022. 18:53
 */


class TabAdapter  (fm: FragmentActivity, private var tabsFrag: MutableList<Fragment>): FragmentStateAdapter(fm) {

    override fun getItemCount()= tabsFrag.size

    override fun createFragment(position: Int)= tabsFrag[position]

    fun getItem(position: Int): Fragment = tabsFrag[position]

}