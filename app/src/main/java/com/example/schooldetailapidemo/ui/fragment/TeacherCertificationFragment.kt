package com.example.schooldetailapidemo.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.FragmentTeacherCertificationBinding
import com.example.schooldetailapidemo.ui.model.TeacherDetailRoot


class TeacherCertificationFragment : Fragment() {
    private lateinit var binding: FragmentTeacherCertificationBinding
    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_teacher_certification, container, false)

        val any = requireArguments().getParcelableArrayList<TeacherDetailRoot.Data.Documents>("documents")
        val list = any as? ArrayList<*>

        for(i in 0 until any!!.size){
            Glide.with(requireContext()).load("http://13.233.39.120/kodris/${any[i].document}").placeholder(R.drawable.certificate).into(binding.certificateImage)
        }
      return  binding.root
    }

}