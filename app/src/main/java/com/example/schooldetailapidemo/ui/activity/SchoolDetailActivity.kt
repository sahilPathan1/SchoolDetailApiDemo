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
import com.example.schooldetailapidemo.databinding.ActivityDetailBinding
import com.example.schooldetailapidemo.ui.adapter.DetailViewPagerAdapter
import com.example.schooldetailapidemo.ui.model.SchoolDetailRoot
import com.example.schooldetailapidemo.ui.model.viewmodel.DetailViewModel
import com.example.schooldetailapidemo.ui.utils.Status
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Float.max
import java.lang.Float.min


@AndroidEntryPoint
class SchoolDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var progressDialog: ProgressDialog
    var id: String = ""
    private lateinit var mScaleGestureDetector: ScaleGestureDetector
    private var mScaleFactor = 1.0f
    private lateinit var pager: ViewPager
    private lateinit var tab: TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        setToolBar()
        progressDialog = ProgressDialog(this)

        if (intent != null) {
            id = intent!!.getStringExtra("id").toString()

            Log.d("id activity--------------------------------", "" + id)
        }

        detailViewModel.getDetailData(id)
        detailViewModel.liveData.observe(this@SchoolDetailActivity) {
            when (it.status) {

                Status.SUCCESS -> {
                    if (it.data!!.status == true) {
                        progressDialog.dismiss()
                        binding.tvSchoolName.text = it.data.data!!.name
                        setViewPAger(it.data)
                        Glide.with(applicationContext)
                            .load("http://13.233.39.120/kodris/" + it.data.data!!.profilePicture)
                            .placeholder(com.example.schooldetailapidemo.R.drawable.ic_launcher_background)
                            .into(binding.shapableImageView)
                        mScaleGestureDetector = ScaleGestureDetector(this, ScaleListener())
                    }
                    binding.tvEstablishDate.text = it.data.data!!.establishedOn

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

    private fun setViewPAger(data: SchoolDetailRoot?) {
        pager = binding.idViewPager
        tab = binding.idTab
        val adapter = DetailViewPagerAdapter(supportFragmentManager, data)
        pager.adapter = adapter
        tab.setupWithViewPager(pager)
    }

    private fun setToolBar() {

        setSupportActionBar(binding.toolbar)
        if (supportActionBar != null) {
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

