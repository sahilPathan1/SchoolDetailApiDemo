package com.example.schooldetailapidemo.ui.repository

import com.example.schooldetailapidemo.ui.model.LogoutModel
import com.example.schooldetailapidemo.ui.retrofit.ApiInterFaceService
import retrofit2.Response
import javax.inject.Inject

class LogOutRepository @Inject constructor(private val apiInterFaceService: ApiInterFaceService) {
    suspend fun logoutUser(): Response<LogoutModel> {
        return apiInterFaceService.logout()
    }
}
