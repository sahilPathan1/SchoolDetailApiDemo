package com.example.schooldetailapidemo.ui.repository

import com.example.schooldetailapidemo.ui.model.RootSchool
import com.example.schooldetailapidemo.ui.retrofit.ApiInterFaceService
import retrofit2.Response
import javax.inject.Inject

class SchoolRepository @Inject constructor(private val apiInterFaceService: ApiInterFaceService) {
    suspend fun getSchoolData(hashMap: HashMap<String,String>): Response<RootSchool> {
        return apiInterFaceService.getRootSchool(hashMap)
    }
}