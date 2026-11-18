package com.example.byd_sushi

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.byd_sushi.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        // status login (lama)
        // SharedPreferences status login (lama)
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        // data registrasi (dari RegisterActivity)
        val regPrefs = getSharedPreferences("register_prefs", MODE_PRIVATE)



        // fitur baru: login biometrik
        binding.btnBiometrik.setOnClickListener { tampilkanBiometrik(sharedPref) }
        // SharedPreferences data registrasi (baru, dari RegisterActivity)
        val regPrefs = getSharedPreferences("register_prefs", MODE_PRIVATE)

        // tombol menuju halaman registrasi
        binding.btnDaftar.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // data hasil registrasi
            val savedUser = regPrefs.getString("username", "") ?: ""
            val savedPass = regPrefs.getString("password", "") ?: ""
            val sudahDaftar = regPrefs.getBoolean("is_registered", false)

            // rule 1: username sama dengan password
            val rule1 = username.isNotEmpty() && username == password
            // rule 2: cocok dengan username & password di SharedPreferences
            val rule2 = sudahDaftar &&
                    username.isNotEmpty() &&
                    username == savedUser &&
                    password == savedPass

            if (rule1 || rule2) {
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                startActivity(Intent(this, BaseActivity::class.java))
                finish()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Silahkan coba lagi")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }
    }

    private fun tampilkanBiometrik(sharedPref: SharedPreferences) {
        // hanya boleh jika sebelumnya sudah pernah login manual
        val usernameTersimpan = sharedPref.getString("username", null)
        if (usernameTersimpan.isNullOrEmpty()) {
            Toast.makeText(this, "Login manual dulu satu kali", Toast.LENGTH_SHORT).show()
            return
        }

        val auth = BiometricManager.Authenticators.BIOMETRIC_STRONG or
                BiometricManager.Authenticators.DEVICE_CREDENTIAL

        if (BiometricManager.from(this).canAuthenticate(auth) != BiometricManager.BIOMETRIC_SUCCESS) {
            Toast.makeText(this, "Biometrik tidak tersedia di perangkat ini", Toast.LENGTH_SHORT).show()
            return
        }

        val executor = ContextCompat.getMainExecutor(this)
        val prompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    sharedPref.edit().putBoolean("isLogin", true).apply()
                    startActivity(Intent(this@AuthActivity, BaseActivity::class.java))
                    finish()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    Toast.makeText(this@AuthActivity, "Gagal: $errString", Toast.LENGTH_SHORT).show()
                }
            })

        val info = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Login Hebepku")
            .setSubtitle("Gunakan sidik jari atau kunci layar")
            .setAllowedAuthenticators(auth)
            .build()

        prompt.authenticate(info)
    }
}