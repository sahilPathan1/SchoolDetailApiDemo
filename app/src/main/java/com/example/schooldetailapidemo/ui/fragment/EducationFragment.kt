package com.example.schooldetailapidemo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.FragmentEducationBinding

class EducationFragment : Fragment() {
    private lateinit var binding: FragmentEducationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_education, container, false
        )
        val education = arguments?.getString("education")
        binding.tvEducation.text = education
        return binding.root
    }
}