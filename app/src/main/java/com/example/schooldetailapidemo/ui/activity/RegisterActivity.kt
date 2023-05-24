package com.example.schooldetailapidemo.ui.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.ActivityRegisterBinding
import com.example.schooldetailapidemo.ui.model.viewmodel.RegisterViewModel
import com.example.schooldetailapidemo.ui.utils.Common
import com.example.schooldetailapidemo.ui.utils.MyDataStore
import com.example.schooldetailapidemo.ui.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModels: RegisterViewModel
    private lateinit var dataStore: MyDataStore
    private lateinit var commonFun: Common
    private lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataStore = MyDataStore(this)
       /* GlobalScope.launch {
            val language = dataStore.getLanguage(this@RegisterActivity)
            LocaleHelper.setLocale(this@RegisterActivity, language)
        }*/
        binding = DataBindingUtil.setContentView(this@RegisterActivity, R.layout.activity_register)
        commonFun = Common(this)
        registerViewModels = ViewModelProvider(this)[RegisterViewModel::class.java]
        window.statusBarColor = ContextCompat.getColor(applicationContext,R.color.blue)
        progressDialog= ProgressDialog(this)
        binding.registerViewModel = registerViewModels
        binding.btnSignUp.setOnClickListener { btnSignUpClick() }
    }

    private fun btnSignUpClick() {
        binding.apply {
            textInputLayouutEmail.error = ""
            textInputLayouutPass.error = ""
            textInputLayoutCPass.error = ""

            if (registerViewModels.validation()) {

                if (commonFun.isNetworkAvailable(application)) {
                    registerViewModels.checkMail()
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        getString(R.string.internet),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            } else {
                binding.textInputLayouutEmail.error = registerViewModels.emailError
                binding.textInputLayouutPass.error = registerViewModels.passwordError
                binding.textInputLayoutCPass.error = registerViewModels.confirmPasswordError
            }
        }

        registerViewModels.liveData.observe(this@RegisterActivity) {
            when (it.status) {
                Status.SUCCESS -> {
                    progressDialog.dismiss()
                    if (it.data!!.status) {
                        Log.e("SuccessFully", "status Success"+it.data.status)
                        Log.e("SuccessFully", "status Success"+it.data.message)
                        Log.d("message---------------------------",""+it.data.message)
                        Toast.makeText(this@RegisterActivity, "Register SuccessFully", Toast.LENGTH_SHORT).show()
                        commonFun.keyBordHide(this@RegisterActivity)
                        startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
                        finish()

                    } else {
                        Log.e("Denied", "status fail"+it.data.status)
                        Log.e("Denied", "status fail"+it.data.message)
                        Log.d("message---------------------------",""+it.data.message)
                        Toast.makeText(this@RegisterActivity, "Register Denied", Toast.LENGTH_SHORT).show()
                        commonFun.keyBordHide(this@RegisterActivity)
                    }
                }

                Status.LOADING -> {
                    progressDialog.setMessage(getString(R.string.dataloading))
                    progressDialog.setCancelable(false)
                    Log.e("Data Register", "status Loading")
                }
                Status.ERROR -> {
                    progressDialog.dismiss()
                    Log.d("StatusError--------------------------------", "" + Status.ERROR)
                }
                else -> {}
            }
        }

    }
}