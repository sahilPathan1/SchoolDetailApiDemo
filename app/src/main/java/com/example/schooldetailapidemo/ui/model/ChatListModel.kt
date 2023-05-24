package com.example.schooldetailapidemo.ui.model

import com.google.gson.annotations.SerializedName

class ChatListModel(

    @SerializedName("status"  ) var status  : Boolean,
    @SerializedName("message" ) var message : String,
    @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf()

) {
    data class Data (

        @SerializedName("id"              ) var id             : Int,
        @SerializedName("name"            ) var name           : String,
        @SerializedName("profile_picture" ) var profilePicture : String,
        @SerializedName("role"            ) var role           : Int,
        @SerializedName("message"         ) var message        : String,
        @SerializedName("created_at"      ) var createdAt      : String,
        @SerializedName("created_ago"     ) var createdAgo     : String,
        @SerializedName("unread_count"    ) var unreadCount    : Int

        ){}
}