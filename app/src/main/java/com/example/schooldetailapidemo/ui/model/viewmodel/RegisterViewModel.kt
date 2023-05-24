package com.example.schooldetailapidemo.ui.model.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.ui.model.CheckMailModel
import com.example.schooldetailapidemo.ui.repository.CheckMailRepository
import com.example.schooldetailapidemo.ui.utils.EMAIL
import com.example.schooldetailapidemo.ui.utils.Resource
import com.example.schooldetailapidemo.ui.utils.ResponseCodeCheck
import com.example.schooldetailapidemo.ui.utils.emailValide
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel(){

    var email: String = ""
    var password: String = ""
    var confirmPassword = ""

    var emailError: String = ""
    var passwordError: String = ""
    var confirmPasswordError:String= ""

    @Inject lateinit var checkMailRepository:CheckMailRepository

    var responseCodeCheck: ResponseCodeCheck = ResponseCodeCheck()
    private var mutabableLiveData: MutableLiveData<Resource<CheckMailModel>> = MutableLiveData()
    var liveData: LiveData<Resource<CheckMailModel>> = mutabableLiveData



    fun validation(): Boolean {

        emailError = ""
        passwordError = ""
        confirmPasswordError = ""

        when {

            email.isEmpty() -> {
                emailError = R.string.email.toString()
                return false
            }
            !email.matches(emailValide.toRegex()) -> {
                emailError = "please valid email"
                return false
            }
            password.isEmpty() -> {
                passwordError = "empty password"
                return false
            }
            confirmPassword.isEmpty() ->{
                confirmPasswordError = "empty confirm password"
                return false
            }
            else -> {
                return true
            }
        }
    }

    fun checkMail() {

        Log.d("email----------------------",email)
        Log.d("password----------------------",password)
        Log.d("password----------------------",confirmPassword)

        val hashMap: HashMap<String, String> = HashMap()
        hashMap.apply {
            put(EMAIL, email)

        }

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    val repository: Response<CheckMailModel> = checkMailRepository.emailCheck(hashMap)
                    mutabableLiveData.postValue(responseCodeCheck.getResponseResult(repository))
                } catch (e: Exception) {
                    Log.d("data_information--------------",e.toString())
                    mutabableLiveData.postValue(
                        Resource.error("",null)
                    )
                }
            }
        }
    }

}