package com.example.schooldetailapidemo.ui.model.viewmodel
import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schooldetailapidemo.ui.model.ChatHistoryModel
import com.example.schooldetailapidemo.ui.repository.ChatHistoryRepository
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
class ChatHistoryViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var chatHistoryRepository: ChatHistoryRepository
    private var responseCodeCheck: ResponseCodeCheck = ResponseCodeCheck()
    private var mutabableLiveData: MutableLiveData<Resource<ChatHistoryModel>> = MutableLiveData()
    var liveData: LiveData<Resource<ChatHistoryModel>> = mutabableLiveData

    fun getChatHistory(receiver_id: String) {
        mutabableLiveData.value = Resource.loading(null)
        val hashMap: HashMap<String, String> = HashMap()
        hashMap["receiver_id"] = receiver_id

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val chatRepository: Response<ChatHistoryModel> =
                    chatHistoryRepository.chatHistory(hashMap)
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
