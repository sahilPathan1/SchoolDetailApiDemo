package com.example.schooldetailapidemo.ui.model.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schooldetailapidemo.ui.model.SchoolDetailRoot
import com.example.schooldetailapidemo.ui.repository.SchoolDetailRepository
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
class DetailViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var schoolDetailRepository: SchoolDetailRepository

    private var responseCodeCheck: ResponseCodeCheck = ResponseCodeCheck()
    private var mutabableLiveData: MutableLiveData<Resource<SchoolDetailRoot>> = MutableLiveData()
    var liveData: LiveData<Resource<SchoolDetailRoot>> = mutabableLiveData

    fun getDetailData(id: String) {
        mutabableLiveData.value = Resource.loading(null)
        val hashMap: HashMap<String, String> = HashMap()
        hashMap["id"] = id

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val mainRepository: Response<SchoolDetailRoot> =
                    schoolDetailRepository.getSchoolDetailData(hashMap)
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