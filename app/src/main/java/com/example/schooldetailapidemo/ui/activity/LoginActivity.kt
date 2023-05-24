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
import com.example.schooldetailapidemo.databinding.ActivityLoginBinding
import com.example.schooldetailapidemo.ui.CustomToastDialog.showSuccessAlert
import com.example.schooldetailapidemo.ui.model.viewmodel.LoginViewModel
import com.example.schooldetailapidemo.ui.utils.Common
import com.example.schooldetailapidemo.ui.utils.MyDataStore
import com.example.schooldetailapidemo.ui.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.io.InputStream

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginVmodal: LoginViewModel
    private lateinit var commonFun: Common
    private lateinit var progressDialog: ProgressDialog
    private lateinit var dataStore: MyDataStore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        dataStore = MyDataStore(this)

        commonFun = Common(this)

        loginVmodal = ViewModelProvider(this)[LoginViewModel::class.java]
        window.statusBarColor = ContextCompat.getColor(applicationContext,R.color.blue)
        progressDialog= ProgressDialog(this)
        binding.loginViewModal = loginVmodal

        binding.apply {
            btnForget.setOnClickListener { Toast.makeText(this@LoginActivity, getString(R.string.click), Toast.LENGTH_SHORT).show()}
            btnLogin.setOnClickListener { onLoginClick() }
            tvSignUp.setOnClickListener {  startActivity(Intent(this@LoginActivity,RegisterActivity::class.java)) }

        }
    }

    private fun onLoginClick() {
        binding.apply {
           /* textInputLayouutEmail.error = ""
            textInputLayouutPass.error = ""*/

            if (loginVmodal.validation()) {

                if (commonFun.isNetworkAvailable(application)) {

                    loginVmodal.loginUser()
                } else {
                    Toast.makeText(this@LoginActivity,"please check your Internet Connection ", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
               /* binding.textInputLayouutEmail.error = loginVmodal.emailError
                binding.textInputLayouutPass.error = loginVmodal.passwordError*/
            }
        }

        loginVmodal.liveData.observe(this@LoginActivity) {
            when (it.status) {
                Status.SUCCESS -> {
                    progressDialog.dismiss()
                    if (it.data!!.status) {
                        showSuccessAlert("Login SuccessFull")
                        Log.e("SuccessFully", "status Success"+it.data.status)
                        Log.e("SuccessFully", "status Success"+it.data.message)


                        Toast.makeText(this@LoginActivity, "login SuccessFully", Toast.LENGTH_SHORT).show()
                        commonFun.keyBordHide(this@LoginActivity)

                        CoroutineScope(Dispatchers.IO).launch {
                            dataStore.isUserLogin(true)
                            dataStore.setToken(it.data.data.token!!)
                            dataStore.setUserId(it.data.data.id!!.toString())
                            Log.d("Token-----------------------------", "status Success"+it.data.data.token!!)
                            Log.d("Id-----------------------------", " Id--------"+it.data.data.id!!.toString())
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finishAffinity()
                        }

                    } else {
                        Log.e("Denied", "status fail"+it.data.status)
                        Log.e("Denied", "status fail"+it.data.message)
                        Toast.makeText(this@LoginActivity, "login Denied", Toast.LENGTH_SHORT).show()
                        commonFun.keyBordHide(this@LoginActivity)
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