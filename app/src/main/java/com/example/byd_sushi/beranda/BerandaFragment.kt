package com.example.byd_sushi.beranda

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu // BARU
import android.view.MenuInflater // BARU
import android.view.MenuItem // BARU
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.byd_sushi.R // BARU
import com.example.byd_sushi.beranda.tutorial.TutorialActivity // BARU
import com.example.byd_sushi.databinding.FragmentBerandaBinding
import com.example.byd_sushi.home.pertemuan_5.WebViewActivity
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class BerandaFragment : Fragment() {

    private var _binding: FragmentBerandaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBerandaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Beranda"

        setHasOptionsMenu(true) // BARU: agar menu Info tampil di toolbar

        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "Pengguna") ?: "Pengguna"
        binding.tvUsername.text = username.replaceFirstChar { it.uppercase() }

        binding.btnInfoWeb.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        // fitur baru: grafik penjualan mingguan
        setupChart()
    }

    // BARU: tampilkan menu Info di toolbar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.beranda_toolbar_menu, menu)
    }

    // BARU: klik ikon Info membuka TutorialActivity
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_tutorial -> {
                startActivity(Intent(requireContext(), TutorialActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupChart() {
        val hari = listOf("Sen", "Sel", "Rab", "Kam", "Jum", "Sab", "Min")
        val nilai = listOf(5f, 7f, 4f, 9f, 6f, 12f, 8f)   // juta Rp (data contoh)

        val entries = nilai.mapIndexed { i, v -> BarEntry(i.toFloat(), v) }
        val dataSet = BarDataSet(entries, "Penjualan (juta Rp)").apply {
            color = Color.parseColor("#5B1F6B")
            valueTextSize = 10f
        }

        binding.barChart.apply {
            data = BarData(dataSet)
            xAxis.valueFormatter = IndexAxisValueFormatter(hari)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1f
            xAxis.setDrawGridLines(false)
            axisRight.isEnabled = false
            axisLeft.axisMinimum = 0f
            description.isEnabled = false
            legend.isEnabled = false
            animateY(800)
            invalidate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}