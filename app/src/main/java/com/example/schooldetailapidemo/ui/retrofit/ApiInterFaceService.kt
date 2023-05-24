package com.example.schooldetailapidemo.ui.retrofit

import com.example.schooldetailapidemo.ui.model.*
import com.example.schooldetailapidemo.ui.utils.*
import retrofit2.Response
import retrofit2.http.*

interface ApiInterFaceService {

    @FormUrlEncoded
    @POST(school_list)
    suspend fun getRootSchool(@FieldMap hashMap: HashMap<String, String>): Response<RootSchool>

    @FormUrlEncoded
    @POST(teacher_list)
    suspend fun getTeacherRoot(@FieldMap hashMap: HashMap<String, String>): Response<TeacherRoot>

    @FormUrlEncoded
    @POST(school_detail)
    suspend fun getSchoolDetailData(@FieldMap hashMap: HashMap<String, String>):Response<SchoolDetailRoot>

    @FormUrlEncoded
    @POST(teacher_detail)
    suspend fun getTeacherDetail(@FieldMap hashMap: HashMap<String, String>):Response<TeacherDetailRoot>

    @FormUrlEncoded
    @POST(login)
    suspend fun loginUser(@FieldMap hashMap: HashMap<String, String>): Response<LoginModel>

    @FormUrlEncoded
    @Headers("Accept:application/json")
    @POST(checkMail)
    suspend fun emailCheck(@FieldMap hashMap: HashMap<String, String>): Response<CheckMailModel>

    @GET(logout)
    suspend fun logout(): Response<LogoutModel>

    @GET(chat_list)
    suspend fun chatList(@Query("page_number") pageNumber: Int): Response<ChatListModel>

    @FormUrlEncoded
    @POST(chat_history)
    suspend fun chatHistory(@FieldMap hashMap: HashMap<String, String>):Response<ChatHistoryModel>


}

