package com.example.schooldetailapidemo.ui.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


class TeacherDetailRoot(
    @SerializedName("status"  ) var status  : Boolean,
    @SerializedName("message" ) var message : String,
    @SerializedName("data"    ) var data    : Data
) {
    data class Data(

        @SerializedName("id") var id: Int,
        @SerializedName("name") var name: String,
        @SerializedName("email") var email: String,
        @SerializedName("role") var role: Int,
        @SerializedName("latitude") var latitude: String,
        @SerializedName("longitude") var longitude: String,
        @SerializedName("education") var education: String,
        @SerializedName("total_experience") var totalExperience: String,
        @SerializedName("experience") var experience: ArrayList<Experience> = arrayListOf(),
        @SerializedName("specialized") var specialized: String,
        @SerializedName("phone_number") var phoneNumber: String,
        @SerializedName("address") var address: String,
        @SerializedName("address_latitude") var addressLatitude: String,
        @SerializedName("address_longitude") var addressLongitude: String,
        @SerializedName("cv") var cv: String,
        @SerializedName("profile_picture") var profilePicture: String,
        @SerializedName("documents") var documents: ArrayList<Documents> = arrayListOf()) {

        @Parcelize
        data class Experience (
            @SerializedName("experience"  ) var experience : String,
            @SerializedName("school_name" ) var schoolName : String,

        ) : Parcelable {}
        @Parcelize
        data class Documents (
            @SerializedName("id"        ) var id        : Int,
            @SerializedName("document"  ) var document  : String,
            @SerializedName("name"      ) var name      : String,
            @SerializedName("extension" ) var extension : String,
        ) : Parcelable {}
    }

}