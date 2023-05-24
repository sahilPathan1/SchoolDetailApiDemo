package com.example.schooldetailapidemo.ui.activity

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.ActivityMainBinding
import com.example.schooldetailapidemo.ui.fragment.HomeFragment
import com.example.schooldetailapidemo.ui.fragment.MessageFragment
import com.example.schooldetailapidemo.ui.fragment.SettingFragment
import com.example.schooldetailapidemo.ui.model.viewmodel.SchoolViewModel
import com.example.schooldetailapidemo.ui.utils.MyDataStore
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dataStore: MyDataStore
    var isSelected = false
    private lateinit var schoolViewModel: SchoolViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataStore = MyDataStore(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        schoolViewModel = ViewModelProvider(this)[SchoolViewModel::class.java]
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            if (item.itemId == binding.bottomNavigationView.selectedItemId) {
                false
            } else {
                when (item.itemId) {
                    R.id.home -> {
                        replaceFragment(HomeFragment())
                    }
                    R.id.message -> {
                        replaceFragment(MessageFragment())
                        isSelected = true
                    }
                    R.id.settings -> {
                        replaceFragment(SettingFragment())

                    }
                }
                true
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flFragment, fragment).commitAllowingStateLoss()
    }

    private fun hideSoftKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
