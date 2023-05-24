package com.example.schooldetailapidemo.ui.model.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schooldetailapidemo.ui.model.RootSchool
import com.example.schooldetailapidemo.ui.model.TeacherRoot
import com.example.schooldetailapidemo.ui.repository.TeacherRepository
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
class TeacherViewModel @Inject constructor() : ViewModel(){

    @Inject
    lateinit var teacherRepository: TeacherRepository

    private var responseCodeCheck: ResponseCodeCheck = ResponseCodeCheck()
    private var mutabableLiveData: MutableLiveData<Resource<TeacherRoot>> = MutableLiveData()
    var liveData: LiveData<Resource<TeacherRoot>> = mutabableLiveData

    fun getDataTeacher(searchText: String? = "") {
        mutabableLiveData.value = Resource.loading(null)
        val hashMap: HashMap<String, String> = HashMap()
        hashMap["page_number"] = "1"
        hashMap["name"] = searchText.toString()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val tacherRepository: Response<TeacherRoot> = teacherRepository.getTeacherData(hashMap)
                mutabableLiveData.postValue(responseCodeCheck.getResponseResult(tacherRepository))

            } catch (e: Exception) {
                Log.d("data_information", e.toString())
                mutabableLiveData.postValue(
                    Resource.error("", null)
                )
            }
        }
    }

}