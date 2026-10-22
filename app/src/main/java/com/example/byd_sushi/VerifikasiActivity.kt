package com.example.byd_sushi

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.byd_sushi.databinding.ActivityVerifikasiBinding

class VerifikasiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVerifikasiBinding
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifikasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences("register_prefs", MODE_PRIVATE)
        tampilkanData()

        binding.btnKembali.setOnClickListener { finish() }

        binding.btnSubmit.setOnClickListener {
            val nama = prefs.getString("nama", "") ?: ""
            val email = prefs.getString("email", "") ?: ""
            val tgl = prefs.getString("tgl_lahir", "") ?: ""
            val gender = prefs.getString("gender", "") ?: ""
            val user = prefs.getString("username", "") ?: ""
            val pass = prefs.getString("password", "") ?: ""
            val confirm = prefs.getString("confirm_password", "") ?: ""

            val semuaTerisi = listOf(nama, email, tgl, gender, user, pass, confirm)
                .all { it.isNotBlank() }

            when {
                !semuaTerisi -> tampilError("Semua data harus diisi")
                pass != confirm -> tampilError("Password dan Confirm Password harus sama")
                else -> {
                    prefs.edit().putBoolean("is_registered", true).apply()
                    startActivity(Intent(this, RegistrasiBerhasilActivity::class.java))
                    finish()
                }
            }
        }
    }

    private fun tampilkanData() {
        binding.tvNama.text = "Nama: ${prefs.getString("nama", "")}"
        binding.tvEmail.text = "Email: ${prefs.getString("email", "")}"
        binding.tvTglLahir.text = "Tanggal Lahir: ${prefs.getString("tgl_lahir", "")}"
        binding.tvGender.text = "Jenis Kelamin: ${prefs.getString("gender", "")}"
        binding.tvUsername.text = "Username: ${prefs.getString("username", "")}"
        binding.tvPassword.text = "Password: ${prefs.getString("password", "")}"
    }

    private fun tampilError(pesan: String) {
        binding.tvError.text = pesan
        binding.tvError.visibility = View.VISIBLE
    }
}