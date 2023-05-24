package com.example.schooldetailapidemo.ui.model

import com.google.gson.annotations.SerializedName


data class CheckMailModel(
    @SerializedName("status") var status: Boolean,
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: ArrayList<String>,
    ) {

}
