package com.example.schooldetailapidemo.ui.repository

import com.example.schooldetailapidemo.ui.model.ChatListModel
import com.example.schooldetailapidemo.ui.retrofit.ApiInterFaceService
import retrofit2.Response
import javax.inject.Inject


class ChatListRepository @Inject constructor(private val service: ApiInterFaceService) {
    suspend fun userChatList(): Response<ChatListModel> {
        return service.chatList(1)
}
}
