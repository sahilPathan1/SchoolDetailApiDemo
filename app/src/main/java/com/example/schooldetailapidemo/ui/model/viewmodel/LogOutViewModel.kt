package com.example.schooldetailapidemo.ui.model.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schooldetailapidemo.ui.model.LogoutModel
import com.example.schooldetailapidemo.ui.repository.LogOutRepository
import com.example.schooldetailapidemo.ui.utils.Resource
import com.example.schooldetailapidemo.ui.utils.ResponseCodeCheck
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class LogOutViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var logOutRepository: LogOutRepository
    lateinit var context: Context
    private var responseCodeCheck: ResponseCodeCheck = ResponseCodeCheck()
    private var mutabableLiveData: MutableLiveData<Resource<LogoutModel>> = MutableLiveData()
    var liveData: LiveData<Resource<LogoutModel>> = mutabableLiveData

    fun logoutUser() {
        mutabableLiveData.value = Resource.loading(null)
       /* val apiInterface = RetrofitInstance.apiInterface
        val logOutRepository = LogOutRepository(apiInterface)*/

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val mainRepository: Response<LogoutModel> =
                    logOutRepository.logoutUser()
                mutabableLiveData.postValue(responseCodeCheck.getResponseResult(mainRepository))

            } catch (e: Exception) {
                Log.d("data_information", e.toString())
                mutabableLiveData.postValue(Resource.error("", null))
            }
        }
    }
}