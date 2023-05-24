package com.example.schooldetailapidemo.ui.model.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schooldetailapidemo.ui.model.ChatListModel
import com.example.schooldetailapidemo.ui.repository.ChatListRepository
import com.example.schooldetailapidemo.ui.utils.Resource
import com.example.schooldetailapidemo.ui.utils.ResponseCodeCheck
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class ChatListViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var chatListRepository: ChatListRepository
    private var responseCodeCheck: ResponseCodeCheck = ResponseCodeCheck()
    private var mutabableLiveData: MutableLiveData<Resource<ChatListModel>> = MutableLiveData()
    var liveData: LiveData<Resource<ChatListModel>> = mutabableLiveData

    fun getChatList() {
        mutabableLiveData.value = Resource.loading(null)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val chatRepository: Response<ChatListModel> =
                    chatListRepository.userChatList()
                mutabableLiveData.postValue(responseCodeCheck.getResponseResult(chatRepository))

            } catch (e: Exception) {
                Log.d("data_information", e.toString())
                mutabableLiveData.postValue(
                    Resource.error("", null)
                )
            }
        }
    }
}
