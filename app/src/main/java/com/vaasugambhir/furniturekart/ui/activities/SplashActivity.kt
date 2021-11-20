package com.vaasugambhir.furniturekart.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.vaasugambhir.furniturekart.R
import com.vaasugambhir.furniturekart.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        start()
    }

    private fun start() {
        object: CountDownTimer(2000, 2000) {
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(0, R.anim.slide_out)
                finish()
            }
        }.start()
    }
}