package com.example.schooldetailapidemo.ui.model

import com.google.gson.annotations.SerializedName

class LogoutModel(
    @SerializedName("status"  ) var status  : Boolean,
    @SerializedName("message" ) var message : String,
    @SerializedName("data"    ) var data    : ArrayList<String> = arrayListOf()
) {}