package com.example.schooldetailapidemo.ui.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


object GlobalSchoolObj {

    var schoolMutableLiveData: MutableLiveData<String> = MutableLiveData()
    var schoolLiveData: LiveData<String> = schoolMutableLiveData
}