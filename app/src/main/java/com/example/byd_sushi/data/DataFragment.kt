package com.example.byd_sushi.data

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.byd_sushi.databinding.FragmentDataBinding
import com.example.byd_sushi.home.pertemuan_5.InputPenjualanActivity

class DataFragment : Fragment() {

    private var _binding: FragmentDataBinding? = null
    private val binding get() = _binding!!

    private lateinit var penjualanAdapter: PenjualanAdapter
    private var kategoriAktif = "Semua"

    private val semuaData = listOf(
        PenjualanModel("Lip Cream Velvet", "30 Sep 2026", 4, "Rp360.000", "Selesai", "Makeup"),
        PenjualanModel("Serum Vitamin C", "30 Sep 2026", 2, "Rp200.000", "Selesai", "Skincare"),
        PenjualanModel("Bedak Tabur Matte", "29 Sep 2026", 6, "Rp420.000", "Selesai", "Makeup"),
        PenjualanModel("Sunscreen SPF 50", "29 Sep 2026", 3, "Rp255.000", "Diproses", "Skincare"),
        PenjualanModel("Kuas Blush", "28 Sep 2026", 5, "Rp275.000", "Selesai", "Makeup"),
        PenjualanModel("Toner Aloe Vera", "28 Sep 2026", 3, "Rp210.000", "Selesai", "Skincare"),
        PenjualanModel("Cushion Foundation", "27 Sep 2026", 2, "Rp260.000", "Diproses", "Makeup"),
        PenjualanModel("Night Cream Repair", "27 Sep 2026", 1, "Rp120.000", "Selesai", "Skincare")
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

        penjualanAdapter = PenjualanAdapter(semuaData) { item ->
            Toast.makeText(
                requireContext(),
                "${item.produk} • ${item.total}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvPenjualan.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = penjualanAdapter
        }

        // Pencarian
        binding.etCari.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                terapkanFilter()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Filter chip
        binding.chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            kategoriAktif = when (checkedIds.firstOrNull()) {
                binding.chipSkincare.id -> "Skincare"
                binding.chipMakeup.id -> "Makeup"
                else -> "Semua"
            }
            terapkanFilter()
        }

        // Tombol tambah (+)
        binding.fabTambah.setOnClickListener {
            startActivity(Intent(requireContext(), InputPenjualanActivity::class.java))
        }
    }

    private fun terapkanFilter() {
        val kata = binding.etCari.text.toString().trim()
        val hasil = semuaData.filter { item ->
            val cocokKategori = kategoriAktif == "Semua" || item.kategori == kategoriAktif
            val cocokKata = item.produk.contains(kata, ignoreCase = true)
            cocokKategori && cocokKata
        }
        penjualanAdapter.updateData(hasil)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}