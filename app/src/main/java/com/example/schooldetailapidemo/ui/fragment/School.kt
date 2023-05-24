package com.example.schooldetailapidemo.ui.fragment

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.FragmentSchoolBinding
import com.example.schooldetailapidemo.ui.activity.SchoolDetailActivity
import com.example.schooldetailapidemo.ui.adapter.SchoolAdapter
import com.example.schooldetailapidemo.ui.model.RootSchool
import com.example.schooldetailapidemo.ui.model.viewmodel.SchoolViewModel
import com.example.schooldetailapidemo.ui.utils.GlobalSchoolObj.schoolLiveData
import com.example.schooldetailapidemo.ui.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class School : Fragment() {
    private var schoolList = ArrayList<RootSchool.Data>()
    private lateinit var binding: FragmentSchoolBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var schoolViewModel: SchoolViewModel
    private lateinit var schoolAdapter:SchoolAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_school, container, false)
        schoolViewModel = ViewModelProvider(this)[SchoolViewModel::class.java]
        progressDialog = ProgressDialog(context)

        schoolLiveData.observe(viewLifecycleOwner){
            Log.d("--------------------------------------",""+it.toString())
            schoolList.clear()
            schoolViewModel.getData(it)
        }

        schoolViewModel.getData()
        schoolViewModel.liveData.observe(viewLifecycleOwner) {

            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data!!.status!!) {
                        progressDialog.dismiss()
                        binding.rvShowData.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                        schoolList.addAll(it.data.data)
                        schoolAdapter =   SchoolAdapter(requireContext(), schoolList) {pos,schoolModel->
                            val id   =  schoolModel[pos].id.toString()
                            Log.d("Tag===================================", "id$id")
                            val intent = Intent(context, SchoolDetailActivity::class.java)
                            intent.putExtra("id", id)
                            startActivity(intent)
                        }
                        binding.rvShowData.adapter = schoolAdapter
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


