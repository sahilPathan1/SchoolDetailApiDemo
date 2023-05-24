package com.example.schooldetailapidemo.ui.utils

import android.content.Context
import android.net.ConnectivityManager

const val USER_LOGIN = "login"

var USER_KEY = "uname"

const val EMAIL_KEY = "prefs"

const val EMAIL = "email"

const val NAME = "name"

const val PASSWORD = "password"

const val ID_KEY = "id"

const val emailValide = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+"

const val passwordValide = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"


const val CHECK_MAIL = "auth/check-email"

//for DataStore of Sharepreference
const val PREFERENCES_NAME = "user_preferences"



fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}