package com.example.schooldetailapidemo.ui.repository

import com.example.schooldetailapidemo.ui.model.TeacherDetailRoot
import com.example.schooldetailapidemo.ui.retrofit.ApiInterFaceService
import retrofit2.Response
import javax.inject.Inject

class TeacherDetailRepository @Inject constructor(private val apiInterFaceService: ApiInterFaceService) {
    suspend fun getTeacherDetailData(hashMap: HashMap<String,String>): Response<TeacherDetailRoot> {
        return apiInterFaceService.getTeacherDetail(hashMap)
}}
