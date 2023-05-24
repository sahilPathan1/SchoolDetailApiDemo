package com.example.schooldetailapidemo.ui.model

import com.google.gson.annotations.SerializedName

class TeacherRoot(
    @SerializedName("status"  ) var status  : Boolean?        = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf()) {


    data class Data (

        @SerializedName("id"                ) var id               : Int,
        @SerializedName("name"              ) var name             : String,
        @SerializedName("email"             ) var email            : String,
        @SerializedName("role"              ) var role             : Int,
        @SerializedName("latitude"          ) var latitude         : String,
        @SerializedName("longitude"         ) var longitude        : String,
        @SerializedName("education"         ) var education        : String,
        @SerializedName("total_experience"  ) var totalExperience  : String,
        @SerializedName("experience"        ) var experience       : ArrayList<Experience> = arrayListOf(),
        @SerializedName("specialized"       ) var specialized      : String,
        @SerializedName("phone_number"      ) var phoneNumber      : String,
        @SerializedName("address"           ) var address          : String,
        @SerializedName("address_latitude"  ) var addressLatitude  : String,
        @SerializedName("address_longitude" ) var addressLongitude : String,
        @SerializedName("cv"                ) var cv               : String,
        @SerializedName("profile_picture"   ) var profilePicture   : String?               = null,
        @SerializedName("documents"         ) var documents        : ArrayList<Documents>  = arrayListOf()) {

        data class Documents(

            @SerializedName("id") var id: Int? = null,
            @SerializedName("document") var document: String? = null,
            @SerializedName("name") var name: String? = null,
            @SerializedName("extension") var extension: String? = null

        )


        data class Experience(

            @SerializedName("experience") var experience: String? = null,
            @SerializedName("school_name") var schoolName: String? = null

        )

    }
}

