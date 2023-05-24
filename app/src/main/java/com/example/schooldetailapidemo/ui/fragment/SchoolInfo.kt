package com.example.schooldetailapidemo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.FragmentSchoolInfoBinding
import com.example.schooldetailapidemo.ui.model.RootSchool


class SchoolInfo() : Fragment() {
    private lateinit var binding: FragmentSchoolInfoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {


        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_school__info, container, false
        )
        val email = arguments?.getString("email")
        val phoneNumber = arguments?.getString("phoneNumber")
        val address = arguments?.getString("address")
        val website = arguments?.getString("website")
        binding.email.text = email
        binding.number.text = phoneNumber
        binding.address.text = address
        binding.website.text = website

        return binding.root
    }
}


