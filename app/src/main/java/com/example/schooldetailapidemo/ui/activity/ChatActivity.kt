package com.example.schooldetailapidemo.ui.activity

import android.R.*
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.ActivityChatBinding
import com.example.schooldetailapidemo.ui.fragment.MessageFragment
import com.example.schooldetailapidemo.ui.model.ChatHistoryModel
import com.example.schooldetailapidemo.ui.model.viewmodel.ChatHistoryViewModel
import com.example.schooldetailapidemo.ui.socket.MessageListAdapter
import com.example.schooldetailapidemo.ui.socket.SocketResponseModel
import com.example.schooldetailapidemo.ui.utils.*
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONObject


@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {
    var type: String = ""
    var sender_id: String = ""
    var receiver_id: String = ""
    var sander_Message:String = ""
    var receiver_Message:String = ""

    private lateinit var socket: Socket
    private var chatHistoryList = ArrayList<ChatHistoryModel.Data>()
    private lateinit var binding: ActivityChatBinding
    private lateinit var messageListAdapter: MessageListAdapter
    private lateinit var dataStore: MyDataStore
    private lateinit var chatHistoryViewModel: ChatHistoryViewModel
    private lateinit var commonFun: Common
    private lateinit var progressDialog: ProgressDialog
    var gson = Gson()
    val sendData: HashMap<String, String> = HashMap()

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)

        setToolbar()

        val backArrow = resources.getDrawable(R.drawable.back_arrow_chat, theme)
        binding.idToolbar.navigationIcon = backArrow

        val intent = intent
        val Image = intent.getStringExtra("Image")
        val name = intent.getStringExtra("Name")
        receiver_id = intent.getStringExtra("receiverId").toString()
        binding.tvName.text = name

        dataStore = MyDataStore(this)
        commonFun = Common(this)

        chatHistoryViewModel = ViewModelProvider(this)[ChatHistoryViewModel::class.java]
        window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.blue)
        progressDialog = ProgressDialog(this)
        binding.rvItem.layoutManager = LinearLayoutManager(this)

        socketUrlConnected()

        sendUserMessage()


        dataStore.getUserId.asLiveData().observe(this) { id ->
            sender_id = id
            Log.d("sender_id--------------------------------", "" + sender_id)
        }
        chatHistoryViewModel.getChatHistory(receiver_id)

        chatHistoryViewModel.liveData.observe(this@ChatActivity) {
            when (it.status) {
                Status.SUCCESS -> {
                    progressDialog.dismiss()
                    if (it.data!!.status) {
                            chatHistoryList.addAll(it.data.data)
                            Log.d("Data------------------------------", it.data.data.toString())
                        messageListAdapter = MessageListAdapter(this@ChatActivity, chatHistoryList)
                        binding.rvItem.adapter = messageListAdapter
                        binding.rvItem.layoutManager?.scrollToPosition(chatHistoryList.size - 1)
                       /* messageListAdapter.notifyDataSetChanged()*/

                    } else {

                        Log.e("Denied", "status fail" + it.data.message)
                        commonFun.keyBordHide(this)
                    }
                }
                Status.LOADING -> {
                    progressDialog.setMessage(getString(R.string.dataloading))
                    progressDialog.setCancelable(false)
                    Log.e("Data Register", "status Loading")
                }
                Status.ERROR -> {
                    progressDialog.dismiss()
                    Log.d("StatusError--------------------------------", "" + Status.ERROR)
                }
                else -> {}
            }
        }
    }


    private fun socketUrlConnected() {
        try {
            socket = IO.socket(SOCKET_URL)
            socket.connect()
            socket.on(Socket.EVENT_CONNECT, onConnect)
            socket.on(Socket.EVENT_DISCONNECT, onDisConnect)
            socket.on(userConnect, onUserConnect)
            socket.on(receiveMessage, onReceiveMessage)
            socket.on(sendMessage, onMessageSend)
        } catch (e: Exception) {
            Log.d("data_information", e.toString())
            Toast.makeText(this, "data_information$e", Toast.LENGTH_SHORT).show()

        }
    }

    private val onConnect = Emitter.Listener {
        runOnUiThread {
            Log.d("TAG", "Connected...")

            val data: HashMap<String, String> = HashMap()
            data["user_id"] = sender_id
            val gson = Gson()
            val obj = JSONObject(gson.toJson(data))
            socket.emit(userConnect, obj)
        }
    }

    private val onDisConnect = Emitter.Listener {
        runOnUiThread {
            Log.d("TAG", "DisConnected...")
        }
    }

    private val onUserConnect = Emitter.Listener {
        runOnUiThread {
            Log.d("TAG", "User Connect...")
        }
    }


    private val onReceiveMessage = Emitter.Listener {
        runOnUiThread {
            Log.d("SOCKET", "Receive on ${it[0]}")

            var data = it[0]
            val chatMessageModel = gson.fromJson(data.toString(), SocketResponseModel::class.java)
            try {
                chatHistoryList.add(
                    ChatHistoryModel.Data(
                       chatMessageModel.id,
                        chatMessageModel.message,
                        chatMessageModel.createdAt,
                        "",
                        chatMessageModel.receiverId.toInt(),
                        chatMessageModel.senderName,
                        "",
                        chatMessageModel.senderProfilePicture,
                        "",
                        "received"
                    )
                )
                Log.d("SOCKET", "Chat Data=>${chatMessageModel}")
                Log.d("Recieve message---------------", "Chat Data=>${chatMessageModel.message}")
                receiver_Message = chatMessageModel.message
                messageListAdapter = MessageListAdapter(this@ChatActivity, chatHistoryList)
                binding.rvItem.adapter = messageListAdapter
                binding.rvItem.layoutManager?.scrollToPosition(chatHistoryList.size - 1)
            } catch (e: Exception) {
                Log.d("SOCKET", "ERROR=>${chatMessageModel}")
            }
        }
    }

    private val onMessageSend = Emitter.Listener {
        runOnUiThread {
            val data = it[0]
            Log.d("SOCKET", "SEND MESSAGE=>$data")
            val chatMessageModel = gson.fromJson(data.toString(), SocketResponseModel::class.java)
            try {
                chatHistoryList.add(
                    ChatHistoryModel.Data(
                        chatMessageModel.id,
                        chatMessageModel.message,
                        chatMessageModel.createdAt,
                        "",
                        chatMessageModel.receiverId.toInt(),
                        chatMessageModel.senderName,
                        "",
                        chatMessageModel.senderProfilePicture,
                        "",
                        "sent"
                    )
                )
                Log.d("SOCKET", "Chat Data=>${chatMessageModel}")
                Log.d("message--------------------", "Chat Data=>${chatMessageModel.message}")
                sander_Message = chatMessageModel.message
                messageListAdapter = MessageListAdapter(this@ChatActivity, chatHistoryList)
                binding.rvItem.adapter = messageListAdapter
                binding.rvItem.layoutManager?.scrollToPosition(chatHistoryList.size - 1)
            } catch (e: Exception) {
                Log.d("SOCKET", "ERROR=>${chatMessageModel}")

            }
        }
    }

    private fun sendUserMessage() {
        binding.apply {


            sandData.setOnClickListener {

                if (socket.connected()) {
                    sendData["sender_id"] = sender_id
                    sendData["receiver_id"] = receiver_id
                    sendData["receiver_type"] = "Parent"
                    sendData["sender_type"] = "Teacher"
                    sendData["message"] = edtSand.text.toString().trim()
                    Log.d("Sand Data------------------", edtSand.text.toString().trim())
                    val gson = Gson()
                    val obj = JSONObject(gson.toJson(sendData))
                    socket.emit(sendMessage, obj)
                }
                edtSand.text!!.clear()
            }
        }
    }


    override fun onDestroy() {
        socket.off(userConnect)
        socket.off(sendMessage, onMessageSend)
        socket.off(receiveMessage, onReceiveMessage)
        socket.disconnect()
        Log.d("data_information", "disconnect")
        super.onDestroy()
    }


    private fun setToolbar() {
        window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.lightYellow)
        setSupportActionBar(binding.idToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
    override fun onSupportNavigateUp(): Boolean {

      /*  val fragment = MessageFragment()
        val bundle = Bundle()


        val  sand =  bundle.putString("sand", sander_Message)

        Log.d("sander_Message----------------------------", sand.toString())

        val r = bundle.putString("receive", receiver_Message)
        Log.d("receiver_Message------------------------------", r.toString())

        fragment.arguments = bundle*/
        onBackPressed()
        return true
    }
}


