package com.example.breathapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.florent37.viewanimator.ViewAnimator
import com.example.breathapplication.databinding.ActivityMainBinding as ActivityMainBinding1

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding1.inflate(layoutInflater)
        setContentView(binding.root)

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