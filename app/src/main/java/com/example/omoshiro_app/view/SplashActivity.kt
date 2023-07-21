package com.example.omoshiro_app.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.omoshiro_app.R
import com.example.omoshiro_app.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        setUi()
    }

    private fun initBinding(){
        binding = ActivitySplashBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)
    }

    private fun setUi(){

        binding.image.visibility = View.INVISIBLE
        setColor(R.color.blue_archive_white, R.color.blue_archive_blue);

        CoroutineScope(Dispatchers.Main).launch {
            delay(1500)
            binding.image.visibility = View.VISIBLE
            setColor(R.color.black, R.color.white);
            delay(3000)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setColor(backgroundColorId: Int, textColorId: Int) {
        binding.let {
            it.rootView.setBackgroundColor(this.resources.getColor(backgroundColorId))
            it.splashTitleText.setTextColor(this.resources.getColor(textColorId))
        }
    }
}