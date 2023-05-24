package com.example.schooldetailapidemo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.FragmentExperianceBinding
import com.example.schooldetailapidemo.ui.model.TeacherDetailRoot


class ExperienceFragment : Fragment() {
    private lateinit var binding: FragmentExperianceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_experiance, container, false)

        val any =
            requireArguments().getParcelableArrayList<TeacherDetailRoot.Data.Experience>("experience")
        val list = any as? ArrayList<*>

        for (i in 0 until any!!.size) {
            binding.tvExperience.text = any[i].experience
            binding.tvTitleSchoolName.text = any[i].schoolName
        }

        return binding.root


    }

}