package com.example.schooldetailapidemo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.ActivityLanguageSelectorBinding
import com.example.schooldetailapidemo.ui.baseactivity.BaseActivity
import com.example.schooldetailapidemo.ui.splashScreen.SplashScreenActivity
import com.example.schooldetailapidemo.ui.utils.MyDataStore
import com.example.schooldetailapidemo.ui.utils.MyDataStore.Companion.count
import kotlinx.coroutines.runBlocking

class LanguageSelectorActivity : BaseActivity() {
    private lateinit var binding: ActivityLanguageSelectorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_language_selector)
        dataStore = MyDataStore(this)
        val languageList = listOf("en", "ar")

        binding.languageSelectSpinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, languageList)

        binding.submit.setOnClickListener {
            val selectedLanguage = languageList[binding.languageSelectSpinner.selectedItemPosition]

            runBlocking {
                dataStore.setLanguage(selectedLanguage)
            }
            startActivity(Intent(this, SplashScreenActivity::class.java))
            finishAffinity()
            Log.d("COUNT", "COUNT=>$count")
        }
    }
}