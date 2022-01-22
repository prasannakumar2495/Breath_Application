package com.example.breathapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import com.example.breathapplication.sharedPreference.Prefs1
import com.github.florent37.viewanimator.ViewAnimator
import java.text.MessageFormat
import com.example.breathapplication.databinding.ActivityMainBinding as ActivityMainBinding1

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding1
    lateinit var prefs: Prefs1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding1.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = Prefs1(this)
        binding.breathsTakenTxt.text =
            android.icu.text.MessageFormat.format("{0} min today", prefs.sessions)

        binding.todayMinusText.text =
            android.icu.text.MessageFormat.format("{0} breathstoday", prefs.breath)
        binding.lastBreathsTxt.text = prefs.date

        startIntroAnimation()
        binding.startButton.setOnClickListener {
            startAnimation()
        }
    }

    private fun startAnimation() {
        ViewAnimator.animate(binding.imageView)
            .alpha(0f, 1f)
            .onStart {
                binding.guideText.text = "Inhale...Exdhale..."
            }
            .decelerate()
            .duration(1000)
            .thenAnimate(binding.imageView)
            .scale(0.0f, 1.5f, 0.2f)
            .rotation(360f)
            .repeatCount(5)
            .accelerate()
            .duration(5000)
            .onStop {
                binding.guideText.text = "Good Job..."
                binding.imageView.scaleX = 1.0f
                binding.imageView.scaleY = 1.0f

                //setting the data from shared preference in Prefs1 class
                prefs.sessions = prefs.sessions + 1
                prefs.breath = prefs.breath + 1
                prefs.setDate(System.currentTimeMillis())

                val handler = Handler()
                val countDownTimer = Runnable {
                    startActivity(Intent(this@MainActivity, MainActivity::class.java))
                    finish()
                }
                handler.postDelayed(countDownTimer, 100)

//                //refresh the activity
//                object : CountDownTimer(2000, 1000) {
//                    override fun onTick(p0: Long) {
//                        TODO("Not yet implemented")
//                    }
//
//                    override fun onFinish() {
//                        startActivity(Intent(applicationContext, MainActivity::class.java))
//                        finish()
//                    }
//
//                }.start()
            }
            .start()
    }

    private fun startIntroAnimation() {
        ViewAnimator.animate(binding.guideText)
            .scale(0.0f, 1.0f)
            .duration(1500)
            .onStart {
                binding.guideText.text = "Breath"
            }
            .start()
    }
}