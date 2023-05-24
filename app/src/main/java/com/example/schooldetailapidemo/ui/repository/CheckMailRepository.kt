package com.example.schooldetailapidemo.ui.repository

import com.example.schooldetailapidemo.ui.model.CheckMailModel
import com.example.schooldetailapidemo.ui.retrofit.ApiInterFaceService
import retrofit2.Response
import javax.inject.Inject

class CheckMailRepository  @Inject constructor(private val service:ApiInterFaceService){
    suspend fun emailCheck(hashMap: HashMap<String,String>):Response<CheckMailModel>{
        return service.emailCheck(hashMap)
    }
}
