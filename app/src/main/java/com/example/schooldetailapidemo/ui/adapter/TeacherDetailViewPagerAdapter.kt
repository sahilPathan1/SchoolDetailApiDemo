package com.example.schooldetailapidemo.ui.adapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.schooldetailapidemo.ui.fragment.Certification
import com.example.schooldetailapidemo.ui.fragment.EducationFragment
import com.example.schooldetailapidemo.ui.fragment.ExperienceFragment
import com.example.schooldetailapidemo.ui.fragment.TeacherCertificationFragment
import com.example.schooldetailapidemo.ui.model.TeacherDetailRoot

class TeacherDetailViewPagerAdapter(fm: FragmentManager, var teacherDetailData: TeacherDetailRoot?) :
    FragmentPagerAdapter(fm) {

    private val COUNT = 3

    override fun getItem(position: Int): Fragment {
        return when (position) {

            0 -> {

                val experienceFragment = ExperienceFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("experience", teacherDetailData?.data!!.experience)
                experienceFragment.arguments = bundle
                return experienceFragment
            }

            1 -> {

                val teacherCertificationFragment = TeacherCertificationFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("documents", teacherDetailData?.data?.documents)
                teacherCertificationFragment.arguments = bundle
                return teacherCertificationFragment
            }

            2 ->{

                val education = EducationFragment()
                val bundle = Bundle()
                bundle.putString("education", teacherDetailData!!.data.education)
                education.arguments = bundle
                return education
            }
            else ->{
                return Certification()
            }
        }
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0-> "Experience"
            1-> "Certification"
            2->"Education"
            else -> {
                return "Tab"
            }
        }
    }
}
