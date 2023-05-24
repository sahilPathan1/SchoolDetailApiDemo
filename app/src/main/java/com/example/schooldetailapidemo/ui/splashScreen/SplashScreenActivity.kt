package com.example.schooldetailapidemo.ui.splashScreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.asLiveData
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.ui.activity.LoginActivity
import com.example.schooldetailapidemo.ui.activity.MainActivity
import com.example.schooldetailapidemo.ui.utils.MyDataStore

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var dataStore: MyDataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        fullScreen()
        dataStore = MyDataStore(this)
        dataStore.isUserLogin.asLiveData().observe(this) {

            Handler(Looper.getMainLooper()).postDelayed({

                if (it == true) {
                    Log.d("status of live data--------------------------------------",""+it)
                    Toast.makeText(this, "trueeeeeeeeeeeeeee", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))

                } else {
                    Log.d("status of live data--------------------------------------",""+it)
                    startActivity(Intent(this,LoginActivity::class.java))
                    Toast.makeText(this, "false", Toast.LENGTH_SHORT).show()
                }
                finishAffinity()
            }, 3000)
        }
    }

    private fun fullScreen() {

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}