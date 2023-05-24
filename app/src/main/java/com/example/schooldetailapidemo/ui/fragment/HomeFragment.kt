package com.example.schooldetailapidemo.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.FragmentHomeBinding
import com.example.schooldetailapidemo.ui.adapter.MainViewPagerAdapter
import com.example.schooldetailapidemo.ui.model.viewmodel.SchoolViewModel
import com.example.schooldetailapidemo.ui.utils.GlobalSchoolObj
import com.example.schooldetailapidemo.ui.utils.MyDataStore
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    var ischeck = false
    private lateinit var tab: TabLayout
    private lateinit var pager: ViewPager
    private lateinit var dataStore: MyDataStore
    private lateinit var binding: FragmentHomeBinding
    private lateinit var schoolViewModel: SchoolViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        dataStore = MyDataStore(requireContext())
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )

        schoolViewModel = ViewModelProvider(this)[SchoolViewModel::class.java]

        pager = binding.pager
        tab = binding.tab

        val adapter = MainViewPagerAdapter(requireFragmentManager())
        pager.adapter = adapter
        tab.setupWithViewPager(pager)

        var isShow = true
        var scrollRange = -1
        Toast.makeText(requireContext(), "Hello", Toast.LENGTH_SHORT).show()
        binding.appBarLayout.addOnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                binding.collapse.title = getString(R.string.title_toolbar)
                isShow = true
            } else if (isShow) {
                binding.collapse.title = " "
                isShow = false
            }
        }

        binding.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                tab.view.setBackgroundResource(R.drawable.tab_stroke)
                Toast.makeText(requireContext(), "tab" + tab.position, Toast.LENGTH_SHORT).show()

                if (tab.position == 0) {
                    ischeck = true
                }
                if (tab.position == 1) {

                    ischeck = false
                }

                if (ischeck) {
                    binding.textInputEditText.text!!.clear()
                    tab.view.setBackgroundResource(R.drawable.tab_stroke)
                    searchByTab()
                }
                if (!ischeck) {
                    binding.textInputEditText.text!!.clear()
                    tab.view.setBackgroundResource(R.drawable.tab_stroke)
                    searchByTab()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.drawable.tab_stroke)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.drawable.tab_stroke)
            }
        })

        return binding.root
    }

    private fun searchByTab() {
        binding.imageSearch.setOnClickListener {
            hideSoftKeyboard(binding.textInputEditText)
            val searchText = binding.textInputEditText.text?.toString()
            GlobalSchoolObj.schoolMutableLiveData.value = searchText.toString()
        }
    }

    private fun hideSoftKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}