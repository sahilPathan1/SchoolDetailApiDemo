package com.example.schooldetailapidemo.ui.repository

import com.example.schooldetailapidemo.ui.model.ChatHistoryModel
import com.example.schooldetailapidemo.ui.retrofit.ApiInterFaceService
import retrofit2.Response
import javax.inject.Inject


class ChatHistoryRepository  @Inject constructor(private val service:ApiInterFaceService){
    suspend fun chatHistory(hashMap: HashMap<String,String>):Response<ChatHistoryModel>{
        return service.chatHistory(hashMap)
    }
}
