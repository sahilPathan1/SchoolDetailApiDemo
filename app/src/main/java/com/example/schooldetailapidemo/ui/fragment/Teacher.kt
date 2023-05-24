package com.example.schooldetailapidemo.ui.fragment

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.FragmentTeacherBinding
import com.example.schooldetailapidemo.ui.activity.SchoolDetailActivity
import com.example.schooldetailapidemo.ui.activity.TeacherDetailActivity
import com.example.schooldetailapidemo.ui.adapter.TeacherAdapter
import com.example.schooldetailapidemo.ui.model.TeacherRoot
import com.example.schooldetailapidemo.ui.model.viewmodel.TeacherViewModel
import com.example.schooldetailapidemo.ui.utils.GlobalSchoolObj
import com.example.schooldetailapidemo.ui.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Teacher : Fragment() {
    private var teacherList = ArrayList<TeacherRoot.Data>()
    private lateinit var binding: FragmentTeacherBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var teacherViewModel: TeacherViewModel
    private lateinit var teacherAdapter: TeacherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_teacher, container, false
        )
        teacherViewModel = ViewModelProvider(this)[TeacherViewModel::class.java]
        progressDialog = ProgressDialog(context)

        GlobalSchoolObj.schoolLiveData.observe(viewLifecycleOwner) {
            Log.d("--------------------------------------", "" + it.toString())
            teacherList.clear()
            teacherViewModel.getDataTeacher(it)
        }
        Toast.makeText(requireContext(), "Hello", Toast.LENGTH_SHORT).show()
        teacherViewModel.getDataTeacher()
        teacherViewModel.liveData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data!!.status!!) {
                        progressDialog.dismiss()
                        binding.rvTeacher.layoutManager =
                            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                        teacherList.addAll(it.data.data)
                        teacherAdapter =
                            TeacherAdapter(requireContext(), teacherList) { pos, teacherModel ->
                                val id   =  teacherModel[pos].id.toString()
                                Log.d("Tag===================================", "id$id")
                                val intent = Intent(context, TeacherDetailActivity::class.java)
                                intent.putExtra("id", id)
                                startActivity(intent)
                            }
                        binding.rvTeacher.adapter = teacherAdapter
                        Log.d(ContentValues.TAG, "" + Status.SUCCESS)
                    }
                }
                Status.LOADING -> {
                    progressDialog.setMessage("Loading...")
                    progressDialog.setCanceledOnTouchOutside(false)
                    progressDialog.show()
                    Log.d(ContentValues.TAG, "Status" + Status.LOADING)
                }
                Status.ERROR -> {
                    progressDialog.dismiss()
                    Log.d(ContentValues.TAG, "Status" + Status.ERROR)
                }
                else -> {}
            }
        }
        return binding.root
    }


}