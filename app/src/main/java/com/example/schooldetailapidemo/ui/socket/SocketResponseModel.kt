package com.example.schooldetailapidemo.ui.socket

import com.google.gson.annotations.SerializedName

class SocketResponseModel(
    @SerializedName("sender_id"              ) var senderId             : String,
    @SerializedName("receiver_id"            ) var receiverId           : String,
    @SerializedName("message"                ) var message              : String,
    @SerializedName("sender_type"            ) var senderType           : String,
    @SerializedName("receiver_type"          ) var receiverType         : String,
    @SerializedName("created_at"             ) var createdAt            : String,
    @SerializedName("sender_name"            ) var senderName           : String,
    @SerializedName("sender_profile_picture" ) var senderProfilePicture : String,
    @SerializedName("role"                   ) var role                 : Int,
    @SerializedName("id"                     ) var id                   : Int,
    @SerializedName("status"                 )var status                :String
){
}