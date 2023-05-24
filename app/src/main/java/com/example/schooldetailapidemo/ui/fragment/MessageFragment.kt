package com.example.schooldetailapidemo.ui.fragment

import android.app.ProgressDialog
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.FragmentMessageBinding
import com.example.schooldetailapidemo.ui.adapter.ChatListAdapter
import com.example.schooldetailapidemo.ui.model.ChatListModel
import com.example.schooldetailapidemo.ui.model.viewmodel.ChatListViewModel
import com.example.schooldetailapidemo.ui.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageFragment : Fragment() {
    private var chatList = ArrayList<ChatListModel.Data>()
    private lateinit var binding: FragmentMessageBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var chatListViewModel: ChatListViewModel
    private lateinit var chatListAdapter: ChatListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_message, container, false)
        chatListViewModel = ViewModelProvider(this)[ChatListViewModel::class.java]
        progressDialog = ProgressDialog(context)
        chatListViewModel.getChatList()
        callChatApi()

        return binding.root
    }

   /* fun updateData(data: String) {
        Toast.makeText(requireContext(), "sander---------------------"+data, Toast.LENGTH_SHORT).show()
    }*/
    private fun callChatApi() {
        chatListViewModel.liveData.observe(viewLifecycleOwner) {

            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data!!.status!!) {
                        progressDialog.dismiss()
                        binding.rvList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                        chatList.addAll(it.data.data)

                        chatListAdapter =   ChatListAdapter(requireContext(), chatList)
                        binding.rvList.adapter = chatListAdapter
                        Log.d(ContentValues.TAG, "" + Status.SUCCESS)
                    }
                }
                Status.LOADING -> {
                    progressDialog.setMessage("Loading...")
                    progressDialog.setCanceledOnTouchOutside(false)
                    progressDialog.show()
                    Log.d(ContentValues.TAG, "Status" + Status.LOADING)
                }
                Status.ERROR -> {
                    progressDialog.dismiss()
                    Log.d(ContentValues.TAG, "Status" + Status.ERROR)
                }
                else -> {}
            }
        }
    }

  /*  override fun onResume() {
        val sander = arguments?.getString("sand")
        val receiver = arguments?.getString("receive")
        Toast.makeText(requireContext(), "sender"+sander, Toast.LENGTH_SHORT).show()
        Toast.makeText(requireContext(), "receiver"+receiver, Toast.LENGTH_SHORT).show()
        super.onResume()
    }*/
}