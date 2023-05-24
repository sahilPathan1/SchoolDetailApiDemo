package com.example.schooldetailapidemo.ui.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.ActivityTeacherDetailBinding
import com.example.schooldetailapidemo.ui.adapter.TeacherDetailViewPagerAdapter
import com.example.schooldetailapidemo.ui.model.TeacherDetailRoot
import com.example.schooldetailapidemo.ui.model.viewmodel.TeacherDetailViewModel
import com.example.schooldetailapidemo.ui.utils.Status
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Float.max
import java.lang.Float.min

@AndroidEntryPoint
class TeacherDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeacherDetailBinding
    private lateinit var teacherDetailViewModel: TeacherDetailViewModel
    private lateinit var progressDialog: ProgressDialog
    var id: String = ""
    private lateinit var mScaleGestureDetector: ScaleGestureDetector
    private var mScaleFactor = 1.0f
    private lateinit var pager: ViewPager
    private lateinit var tab: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_teacher_detail)
        teacherDetailViewModel = ViewModelProvider(this)[TeacherDetailViewModel::class.java]
        setToolBar()
        progressDialog = ProgressDialog(this)

        if (intent != null) {
            id = intent!!.getStringExtra("id").toString()
            Log.d("id activity--------------------------------", "" + id)
        }

        teacherDetailViewModel.getTeacherDetailData(id)
        teacherDetailViewModel.liveData.observe(this@TeacherDetailActivity) {
            when (it.status) {

                Status.SUCCESS -> {
                    if (it.data!!.status) {
                        progressDialog.dismiss()
                        binding.tvSchoolName.text = it.data.data.name
                        setViewPager(it.data)
                        Glide.with(applicationContext)
                            .load("http://13.233.39.120/kodris/" + it.data.data.profilePicture)
                            .placeholder(com.example.schooldetailapidemo.R.drawable.ic_launcher_background)
                            .into(binding.shapableImageView)
                        mScaleGestureDetector = ScaleGestureDetector(this, ScaleListener())
                    }
                    binding.tvSpecialixationName.text = it.data.data.specialized

                }
                Status.LOADING -> {
                    progressDialog.setMessage("Loading...")
                    progressDialog.setCanceledOnTouchOutside(false)
                    progressDialog.show()
                    Log.d("Status LOADING", "" + Status.LOADING)
                }
                Status.ERROR -> {
                    progressDialog.dismiss()
                    Log.d("Status Error", "" + Status.ERROR)
                }
                else -> {}
            }

        }



        binding.idTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.drawable.detail_tab_design)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.drawable.detail_tab_design)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.drawable.detail_tab_design)
            }
        })
    }

    private fun setViewPager(data: TeacherDetailRoot?) {
        pager = binding.idViewPager
        tab = binding.idTab
        val adapter = TeacherDetailViewPagerAdapter(supportFragmentManager,data)
        pager.adapter = adapter
        tab.setupWithViewPager(pager)
    }

    private fun setToolBar() {

        setSupportActionBar(binding.toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.title = getString(R.string.titleToolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        mScaleGestureDetector.onTouchEvent(motionEvent)
        return true
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = max(0.1f, min(mScaleFactor, 10.0f))
            binding.shapableImageView.scaleX = mScaleFactor
            binding.shapableImageView.scaleY = mScaleFactor
            return true
        }
    }
}