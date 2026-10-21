package com.example.byd_sushi.pertemuan_5

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.byd_sushi.R
import com.example.byd_sushi.databinding.ActivityInputPenjualanBinding

class InputPenjualanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputPenjualanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInputPenjualanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Input Data Penjualan"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        binding.btnSimpan.setOnClickListener {
            val produk = binding.etProduk.text.toString().trim()
            val jumlah = binding.etJumlah.text.toString().toIntOrNull()
            val harga = binding.etHarga.text.toString().toIntOrNull()

            if (produk.isEmpty() || jumlah == null || harga == null) {
                Toast.makeText(this, "Produk, jumlah, dan harga wajib diisi", Toast.LENGTH_SHORT).show()
            } else {
                val total = jumlah * harga
                val totalRp = "Rp" + "%,d".format(total).replace(',', '.')
                Toast.makeText(this, "Tersimpan. Total $totalRp", Toast.LENGTH_LONG).show()
                finish()
            }
        }

        binding.btnBatal.setOnClickListener { finish() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            true
        } else super.onOptionsItemSelected(item)
    }
}