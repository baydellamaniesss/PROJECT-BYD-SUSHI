package com.example.byd_sushi

import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.byd_sushi.databinding.ActivityRegisterBinding
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences("register_prefs", MODE_PRIVATE)
        loadData()

        binding.etTanggalLahir.setOnClickListener { showDatePicker() }

        binding.btnSelanjutnya.setOnClickListener {
            saveData()
            startActivity(Intent(this, VerifikasiActivity::class.java))
        }
    }

    private fun showDatePicker() {
        val cal = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, y, m, d ->
                binding.etTanggalLahir.setText(String.format("%02d/%02d/%04d", d, m + 1, y))
            },
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun saveData() {
        val gender = when (binding.rgGender.checkedRadioButtonId) {
            R.id.rbLaki -> "Laki-laki"
            R.id.rbPerempuan -> "Perempuan"
            else -> ""
        }
        prefs.edit().apply {
            putString("nama", binding.etNama.text.toString())
            putString("email", binding.etEmail.text.toString())
            putString("tgl_lahir", binding.etTanggalLahir.text.toString())
            putString("gender", gender)
            putString("username", binding.etUsername.text.toString())
            putString("password", binding.etPassword.text.toString())
            putString("confirm_password", binding.etConfirmPassword.text.toString())
            apply()
        }
    }

    private fun loadData() {
        binding.etNama.setText(prefs.getString("nama", ""))
        binding.etEmail.setText(prefs.getString("email", ""))
        binding.etTanggalLahir.setText(prefs.getString("tgl_lahir", ""))
        binding.etUsername.setText(prefs.getString("username", ""))
        binding.etPassword.setText(prefs.getString("password", ""))
        binding.etConfirmPassword.setText(prefs.getString("confirm_password", ""))
        when (prefs.getString("gender", "")) {
            "Laki-laki" -> binding.rbLaki.isChecked = true
            "Perempuan" -> binding.rbPerempuan.isChecked = true
        }
    }
}