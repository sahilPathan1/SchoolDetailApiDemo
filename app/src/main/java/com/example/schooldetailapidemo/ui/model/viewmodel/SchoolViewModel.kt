package com.example.schooldetailapidemo.ui.model.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schooldetailapidemo.ui.model.RootSchool
import com.example.schooldetailapidemo.ui.repository.SchoolRepository
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
class SchoolViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var schoolRepository: SchoolRepository
    private var responseCodeCheck: ResponseCodeCheck = ResponseCodeCheck()
    private var mutabableLiveData: MutableLiveData<Resource<RootSchool>> = MutableLiveData()
    var liveData: LiveData<Resource<RootSchool>> = mutabableLiveData


    fun getData(searchText: String? = "") {
        mutabableLiveData.value = Resource.loading(null)
        val hashMap: HashMap<String, String> = HashMap()
        hashMap["page_number"] = "1"
        hashMap["name"] = searchText ?: ""
        Log.d("search text ----------------------------+", "" + searchText)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val mainRepository: Response<RootSchool> = schoolRepository.getSchoolData(hashMap)
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