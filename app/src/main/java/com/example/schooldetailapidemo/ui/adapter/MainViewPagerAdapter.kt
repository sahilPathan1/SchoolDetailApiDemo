package com.example.schooldetailapidemo.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.schooldetailapidemo.ui.fragment.School
import com.example.schooldetailapidemo.ui.fragment.Teacher

class MainViewPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    private val COUNT = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {

            0 -> {
                return Teacher()
            }

            1 -> {
                return School()
            }
            else -> {
                return Teacher()
            }
        }
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Teachers"
            1 -> "Schools"

            else -> {
                return "Teachers"
            }
        }

    }
}
