package com.example.schooldetailapidemo.ui.model

import com.google.gson.annotations.SerializedName

class RootSchool(
    @SerializedName("status") var status: Boolean?,
    @SerializedName("message") var message: String?,
    @SerializedName("data") var data: ArrayList<Data> = arrayListOf(),
) {


    data class Data(
        @SerializedName("id") var id: Int?,
        @SerializedName("name") var name: String?,
        @SerializedName("email") var email: String?,
        @SerializedName("role") var role: Int?,
        @SerializedName("latitude") var latitude: String?,
        @SerializedName("longitude") var longitude: String?,
        @SerializedName("is_setup_profile") var isSetupProfile: String?,
        @SerializedName("established_on") var establishedOn: String?,
        @SerializedName("total_teachers") var totalTeachers: String?,
        @SerializedName("standards") var standards: String?,
        @SerializedName("subjects") var subjects: String?,
        @SerializedName("courses") var courses: String?,
        @SerializedName("phone_number") var phoneNumber: String?,
        @SerializedName("address") var address: String?,
        @SerializedName("address_latitude") var addressLatitude: String?,
        @SerializedName("address_longitude") var addressLongitude: String?,
        @SerializedName("website") var website: String?,
        @SerializedName("profile_picture") var profilePicture: String?,
        @SerializedName("documents") var documents: ArrayList<Documents> = arrayListOf(),

        ) {
        data class Documents(
            @SerializedName("id") var id: Int,
            @SerializedName("document") var document: String,
            @SerializedName("name") var name: String,
            @SerializedName("extension") var extension: String,
            )
    }
}
