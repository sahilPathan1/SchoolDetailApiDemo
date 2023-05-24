package com.example.schooldetailapidemo.ui.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


class SchoolDetailRoot(
    @SerializedName("status"  ) var status  : Boolean? = null,
    @SerializedName("message" ) var message : String?  = null,
    @SerializedName("data"    ) var data    : Data?    = Data()
){


    data class Data (

        @SerializedName("id"                ) var id               : Int?                 = null,
        @SerializedName("name"              ) var name             : String?              = null,
        @SerializedName("email"             ) var email            : String?              = null,
        @SerializedName("role"              ) var role             : Int?                 = null,
        @SerializedName("latitude"          ) var latitude         : String?              = null,
        @SerializedName("longitude"         ) var longitude        : String?              = null,
        @SerializedName("is_setup_profile"  ) var isSetupProfile   : String?              = null,
        @SerializedName("established_on"    ) var establishedOn    : String?              = null,
        @SerializedName("total_teachers"    ) var totalTeachers    : String?              = null,
        @SerializedName("standards"         ) var standards        : String?              = null,
        @SerializedName("subjects"          ) var subjects         : String?              = null,
        @SerializedName("courses"           ) var courses          : String?              = null,
        @SerializedName("phone_number"      ) var phoneNumber      : String?              = null,
        @SerializedName("address"           ) var address          : String?              = null,
        @SerializedName("address_latitude"  ) var addressLatitude  : String?              = null,
        @SerializedName("address_longitude" ) var addressLongitude : String?              = null,
        @SerializedName("website"           ) var website          : String?              = null,
        @SerializedName("profile_picture"   ) var profilePicture   : String?              = null,
        @SerializedName("documents"         ) var documents        : ArrayList<Documents> = arrayListOf()

    ){
        @Parcelize
        data class Documents (

            @SerializedName("id"        ) var id        : Int?    = null,
            @SerializedName("document"  ) var document  : String? = null,
            @SerializedName("name"      ) var name      : String? = null,
            @SerializedName("extension" ) var extension : String? = null

        ) : Parcelable
    }
}
