package com.example.schooldetailapidemo.ui.CustomToastDialog

import android.app.Activity
import android.os.Handler
import android.os.Looper
import com.example.schooldetailapidemo.R


var isShown = false

fun Activity.showSuccessAlert(message: String = "") {
    if (isShown){
        //do something
        return
    }
    isShown = true

    SnacyAlert.create(this)
        .setText(message)
//        .setTitle(BaseActivity.language?.getLanguage(getString(R.string.success)).toString())
        .setBackgroundColorRes(R.color.green)
        .setDuration(1500)
        .showIcon(true)
        .setTitleVisibility()
        .setIcon(R.drawable.success)
        .show()

    Handler(Looper.myLooper()!!).postDelayed(
        Runnable {
            isShown = false
        },
        1500
    )
}



fun Activity.showFailAlert(message: String = "") {
    if (isShown){
        //do something
        return
    }
    isShown = true

    SnacyAlert.create(this)
        .setText(message)
//        .setTitle(BaseActivity.language?.getLanguage(getString(R.string.success)).toString())
        .setBackgroundColorRes(R.color.red)
        .setDuration(1500)
        .showIcon(true)
        .setTitleVisibility()
        .setIcon(R.drawable.baseline_block_24)
        .show()

    Handler(Looper.myLooper()!!).postDelayed(
        Runnable {
            isShown = false
        },
        5000
    )
}









