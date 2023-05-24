package com.example.schooldetailapidemo.ui.repository

import com.example.schooldetailapidemo.ui.model.LoginModel
import com.example.schooldetailapidemo.ui.retrofit.ApiInterFaceService
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(private val service: ApiInterFaceService) {
    suspend fun loginUser(hashMap: HashMap<String, String>): Response<LoginModel> {
        return service.loginUser(hashMap)
    }
}