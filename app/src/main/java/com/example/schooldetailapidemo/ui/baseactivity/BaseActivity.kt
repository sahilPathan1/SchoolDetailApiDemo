package com.example.schooldetailapidemo.ui.baseactivity

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.example.schooldetailapidemo.ui.utils.MyDataStore
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {


    @Inject
    lateinit var dataStore: MyDataStore

    lateinit var activity:Activity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataStore.getLanguage.asLiveData().observe(this){ language->
            Log.d("language-------------------------------------",language)
            chooseLanguage(language)
        }

    }

    private fun chooseLanguage(language: String) {
        val myLocale = Locale(language)
        val res: Resources = getResources()

        val dm: DisplayMetrics = res.getDisplayMetrics()
        val conf: Configuration = res.getConfiguration()
        conf.setLocale(myLocale) // API 17+ only.
        conf.setLayoutDirection(myLocale)
        res.updateConfiguration(conf, dm)
    }


}