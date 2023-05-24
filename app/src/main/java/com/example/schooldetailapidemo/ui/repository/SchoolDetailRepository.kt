package com.example.schooldetailapidemo.ui.repository

import com.example.schooldetailapidemo.ui.model.SchoolDetailRoot
import com.example.schooldetailapidemo.ui.retrofit.ApiInterFaceService
import retrofit2.Response
import javax.inject.Inject

class SchoolDetailRepository  @Inject constructor(private val apiInterFaceService: ApiInterFaceService) {
    suspend fun getSchoolDetailData(hashMap: HashMap<String,String>): Response<SchoolDetailRoot> {
        return apiInterFaceService.getSchoolDetailData(hashMap)
    }
}