package com.example.schooldetailapidemo.ui.model

import com.google.gson.annotations.SerializedName


data class LoginModel(

    @SerializedName("status") var status: Boolean,
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: Data,

    ) {

    data class Data(

        @SerializedName("id") var id: Int?,
        @SerializedName("name") var name: String?,
        @SerializedName("email") var email: String?,
        @SerializedName("role") var role: Int?,
        @SerializedName("latitude") var latitude: String?,
        @SerializedName("longitude") var longitude: String?,
        @SerializedName("category") var category: String?,
        @SerializedName("category_id") var categoryId: Int?,
        @SerializedName("sub_category") var subCategory: String?,
        @SerializedName("sub_category_id") var subCategoryId: Int?,
        @SerializedName("school") var school: String?,
        @SerializedName("country") var country: String?,
        @SerializedName("phone_code") var phoneCode: String?,
        @SerializedName("language") var language: String?,
        @SerializedName("is_setup_profile") var isSetupProfile: String?,
        @SerializedName("phone_number") var phoneNumber: String?,
        @SerializedName("address") var address: String?,
        @SerializedName("address_latitude") var addressLatitude: String?,
        @SerializedName("address_longitude") var addressLongitude: String?,
        @SerializedName("profile_picture") var profilePicture: String?,
        @SerializedName("main_subscription_plan_status") var mainSubscriptionPlanStatus: Boolean?,
        @SerializedName("cv_validity_status") var cvValidityStatus: Boolean?,
        @SerializedName("school_validity_status") var schoolValidityStatus: Boolean?,
        @SerializedName("payment_status") var paymentStatus: Int?,
        @SerializedName("membership_status") var membershipStatus: Boolean?,
        @SerializedName("is_display_session") var isDisplaySession: Boolean?,
        @SerializedName("token") var token: String?,
    ) {}
}