package com.example.schooldetailapidemo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.FragmentTeachingsBinding

class Teachings : Fragment() {

private lateinit var binding:FragmentTeachingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_teachings, container, false)
        val totalTeachers = arguments?.getString("totalTeachers")
        val subjects = arguments?.getString("subjects")
        val standards = arguments?.getString("standards")
        val courses = arguments?.getString("courses")
        binding.teacher.text = totalTeachers
        binding.subject.text = subjects
        binding.standard.text = standards
        binding.cource.text = courses

        return binding.root
    }
}

