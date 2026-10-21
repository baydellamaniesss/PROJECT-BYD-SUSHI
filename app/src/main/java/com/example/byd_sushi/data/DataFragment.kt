package com.example.byd_sushi.data

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.byd_sushi.R
import com.example.byd_sushi.databinding.FragmentDataBinding
import com.example.byd_sushi.databinding.ItemPenjualanBinding
import com.example.byd_sushi.home.pertemuan_5.InputPenjualanActivity

data class Transaksi(
    val produk: String,
    val info: String,
    val harga: String,
    val status: String
)

class DataFragment : Fragment() {

    private var _binding: FragmentDataBinding? = null
    private val binding get() = _binding!!

    private val dataTransaksi = listOf(
        Transaksi("Lip Cream Velvet", "30 Sep 2026 · 4 pcs", "Rp360.000", "Selesai"),
        Transaksi("Serum Vitamin C", "30 Sep 2026 · 2 pcs", "Rp200.000", "Selesai"),
        Transaksi("Bedak Tabur Matte", "29 Sep 2026 · 6 pcs", "Rp420.000", "Selesai"),
        Transaksi("Sunscreen SPF 50", "29 Sep 2026 · 3 pcs", "Rp255.000", "Diproses"),
        Transaksi("Kuas Blush", "28 Sep 2026 · 5 pcs", "Rp275.000", "Selesai")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Data Penjualan"

        dataTransaksi.forEach { item ->
            val row = ItemPenjualanBinding.inflate(layoutInflater, binding.listPenjualan, false)
            row.tvProduk.text = item.produk
            row.tvInfo.text = item.info
            row.tvHarga.text = item.harga
            row.tvStatus.text = item.status
            val warna = if (item.status == "Selesai") R.color.teal_accent else R.color.gold
            row.tvStatus.setTextColor(ContextCompat.getColor(requireContext(), warna))
            binding.listPenjualan.addView(row.root)
        }

        binding.fabTambah.setOnClickListener {
            startActivity(Intent(requireContext(), InputPenjualanActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}