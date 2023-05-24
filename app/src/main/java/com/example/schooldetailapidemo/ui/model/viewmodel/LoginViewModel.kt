package com.example.schooldetailapidemo.ui.model.viewmodel

import android.util.Log
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schooldetailapidemo.ui.model.LoginModel
import com.example.schooldetailapidemo.ui.repository.LoginRepository
import com.example.schooldetailapidemo.ui.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    var email: String = ""
    var password: String = ""

    var emailError = ObservableField("")
    var passwordError = ObservableField("")

    @Inject
    lateinit var loginRepository: LoginRepository
    private var responseCodeCheck: ResponseCodeCheck = ResponseCodeCheck()
    private var mutabableLiveData: MutableLiveData<Resource<LoginModel>> = MutableLiveData()
    var liveData: LiveData<Resource<LoginModel>> = mutabableLiveData


    fun validation(): Boolean {

        emailError.set("")
        passwordError.set("")

        when {

            email.isEmpty() -> {
                emailError.set("empty email")
                return false
            }
            !email.matches(emailValide.toRegex()) -> {

                emailError.set("please valid email")
                return false
            }
            password.isEmpty() -> {
                passwordError.set("empty password")
                return false
            }
            else -> {
                return true
            }
        }
    }

    fun loginUser() {
        Log.d("email----------------------", email)
        Log.d("password----------------------", password)

        val hashMap: HashMap<String, String> = HashMap()
        hashMap.apply {
            put(EMAIL, email)
            put(PASSWORD, password)
            hashMap["role"] = "3"
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val mainRepository: Response<LoginModel> =
                    loginRepository.loginUser(hashMap)
                mutabableLiveData.postValue(responseCodeCheck.getResponseResult(mainRepository))

            } catch (e: Exception) {
                Log.d("data_information", e.toString())
                mutabableLiveData.postValue(
                    Resource.error("", null)
                )
            }
        }
    }
}