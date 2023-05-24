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
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.FragmentSettingBinding
import com.example.schooldetailapidemo.ui.activity.LanguageSelectorActivity
import com.example.schooldetailapidemo.ui.model.viewmodel.LogOutViewModel
import com.example.schooldetailapidemo.ui.splashScreen.SplashScreenActivity
import com.example.schooldetailapidemo.ui.utils.MyDataStore
import com.example.schooldetailapidemo.ui.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingFragment : Fragment() {
    private lateinit var progressDialog: ProgressDialog
    private lateinit var logoutViewModel: LogOutViewModel
    private lateinit var binding: FragmentSettingBinding
    private lateinit var datastore:MyDataStore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        logoutViewModel = ViewModelProvider(this)[LogOutViewModel::class.java]
        progressDialog = ProgressDialog(context)
        datastore = MyDataStore(requireContext())
        binding.tvChangeLanguageImage.setOnClickListener {
        startActivity(Intent(requireContext(),LanguageSelectorActivity::class.java))
        }
        binding.topMainContainer.setOnClickListener{}
        binding.tvLogOut.setOnClickListener{
            logoutApiCall()
        }
        return binding.root
    }

    private fun logoutApiCall() {
        logoutViewModel.logoutUser()
        logoutViewModel.liveData.observe(viewLifecycleOwner) {

            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data!!.status!!) {

                        progressDialog.dismiss()

                        CoroutineScope(Dispatchers.IO).launch {
                            datastore.isUserLogin(false)
                            datastore.deleteAllPreferences()
                            val intent = Intent (requireContext(), SplashScreenActivity::class.java)
                            startActivity(intent)
                        }
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
    }
}