package com.example.schooldetailapidemo.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.schooldetailapidemo.ui.fragment.Certification
import com.example.schooldetailapidemo.ui.fragment.SchoolInfo
import com.example.schooldetailapidemo.ui.fragment.Teachings
import com.example.schooldetailapidemo.ui.model.SchoolDetailRoot

class DetailViewPagerAdapter(fm: FragmentManager, var schoolModel: SchoolDetailRoot?) :
    FragmentPagerAdapter(fm) {

    private val COUNT = 3

    override fun getItem(position: Int): Fragment {
        return when (position) {

            0 -> {
                val schoolInfoFragment = SchoolInfo()
                val bundle = Bundle()
                bundle.putString("email", schoolModel!!.data!!.email)
                bundle.putString("phoneNumber", schoolModel!!.data!!.phoneNumber)
                bundle.putString("address", schoolModel!!.data!!.address)
                bundle.putString("website", schoolModel!!.data!!.website)
                schoolInfoFragment.arguments = bundle
                return schoolInfoFragment
            }

            1 -> {
                val certificate = Certification()
                val bundle = Bundle()
                bundle.putParcelableArrayList("documents", schoolModel?.data?.documents)
                certificate.arguments = bundle
                return certificate
            }

            2 ->{
                val teachings = Teachings()
                val bundle = Bundle()
                bundle.putString("totalTeachers", schoolModel!!.data!!.totalTeachers)
                bundle.putString("subjects", schoolModel!!.data!!.subjects)
                bundle.putString("standards", schoolModel!!.data!!.standards)
                bundle.putString("courses", schoolModel!!.data!!.courses)
                teachings.arguments = bundle
                return teachings
            }
            else ->{
              return SchoolInfo()
            }
        }
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0-> "School Info"
            1-> "Certification"
            2->"Teachings"
            else -> {
                return "Tab"
            }
        }

    }
}
