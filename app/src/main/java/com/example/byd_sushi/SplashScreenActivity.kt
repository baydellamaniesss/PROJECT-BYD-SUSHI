package com.example.byd_sushi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // [B] jalankan coroutine, tahan splash 2 detik
        lifecycleScope.launch {
            delay(2000)

            // [C] baca status login dari SharedPreferences "user_pref"
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            val isLogin = sharedPref.getBoolean("isLogin", false)

            // [D] percabangan isLogin
            if (isLogin) {
                // [E] sudah login -> MainActivity
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            } else {
                // [F] belum login -> AuthActivity
                startActivity(Intent(this@SplashScreenActivity, AuthActivity::class.java))
            }
            finish()
        }
    }
}