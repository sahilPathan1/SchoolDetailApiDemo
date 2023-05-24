package com.example.schooldetailapidemo.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.FragmentCertificationBinding
import com.example.schooldetailapidemo.ui.model.SchoolDetailRoot


@Suppress("DEPRECATION")
class Certification : Fragment() {


    private lateinit var binding: FragmentCertificationBinding
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_certification, container, false)

        val any = requireArguments().getParcelableArrayList<SchoolDetailRoot.Data.Documents>("documents")
        val list = any as? ArrayList<*>



        for (i in 0 until any!!.size) {

            val url = "http://13.233.39.120/kodris/${any[i].document.toString()}"
            val extention = any[i].extension
            val isImage = url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".png")

            if (extention == "pdf") {

                binding.imageView.setImageResource(R.drawable.pdf_icon)

                binding.imageView.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setDataAndType(Uri.parse(url.toString()), "application/pdf")
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }

            } else if (isImage) {
                Glide.with(requireContext())
                    .load(url + any[i].document.toString())
                    .placeholder(R.drawable.profile)
                    .into(binding.imageView)
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.otherCcontent),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return binding.root
    }
}
