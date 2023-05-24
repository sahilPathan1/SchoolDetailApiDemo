package com.example.schooldetailapidemo.ui.model

import com.google.gson.annotations.SerializedName

class ChatHistoryModel(

    @SerializedName("status"             ) var status           : Boolean,
    @SerializedName("message"            ) var message          : String,
    @SerializedName("data"               ) var data             : ArrayList<Data>   = arrayListOf(),
    @SerializedName("receiver_user_data" ) var receiverUserData : ReceiverUserData
) {
    data class ReceiverUserData (

        @SerializedName("id"              ) var id             : Int,
        @SerializedName("name"            ) var name           : String,
        @SerializedName("profile_picture" ) var profilePicture : String,
        @SerializedName("role"            ) var role           : Int

    ){
    }
    data class Data (

        @SerializedName("id"                       ) var id                     : Int,
        @SerializedName("message"                  ) var message                : String,
        @SerializedName("created_at"               ) var createdAt              : String,
        @SerializedName("created_ago"              ) var createdAgo             : String,
        @SerializedName("receiver_id"              ) var receiverId             : Int,
        @SerializedName("sender_name"              ) var senderName             : String,
        @SerializedName("receiver_name"            ) var receiverName           : String,
        @SerializedName("sender_profile_picture"   ) var senderProfilePicture   : String,
        @SerializedName("receiver_profile_picture" ) var receiverProfilePicture : String,
        @SerializedName("status"                   ) var status                 : String

    ){

    }
}




