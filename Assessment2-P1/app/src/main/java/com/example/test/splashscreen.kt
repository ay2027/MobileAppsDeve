package com.example.test

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView

class SplashScreen : AppCompatActivity() {

    private lateinit var imgLogo: ImageView
    private lateinit var tvSplashText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        imgLogo = findViewById(R.id.imgLogo)
        tvSplashText = findViewById(R.id.tvSplashText)

        // Load animations
        val moveUpFadeIn = AnimationUtils.loadAnimation(this, R.anim.move_up_fade_in)
        val moveDownFadeIn = AnimationUtils.loadAnimation(this, R.anim.move_down_fade_in)
        val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        // Start move/fade in animations together
        imgLogo.startAnimation(moveUpFadeIn)
        tvSplashText.startAnimation(moveDownFadeIn)

        // After 1.5 seconds (animation duration + delay), fade out both
        Handler(Looper.getMainLooper()).postDelayed({

            imgLogo.startAnimation(fadeOut)
            tvSplashText.startAnimation(fadeOut)

        }, 2000)

        // After fade out complete (1s later), launch MainActivity
        Handler(Looper.getMainLooper()).postDelayed({

            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()

        }, 3000) // 1.5s + 1s fade out = 2.5s total splash duration
    }
}
