package com.example.schooldetailapidemo.ui.repository

import com.example.schooldetailapidemo.ui.model.TeacherRoot
import com.example.schooldetailapidemo.ui.retrofit.ApiInterFaceService
import retrofit2.Response
import javax.inject.Inject

class TeacherRepository @Inject constructor(private val apiInterFaceService: ApiInterFaceService) {
    suspend fun getTeacherData(hashMap: HashMap<String, String>): Response<TeacherRoot> {
        return apiInterFaceService.getTeacherRoot(hashMap)
    }
}