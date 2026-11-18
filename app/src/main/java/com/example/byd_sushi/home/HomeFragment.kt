package com.example.byd_sushi.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.byd_sushi.DashboardActivity
import com.example.byd_sushi.DetailActivity
import com.example.byd_sushi.databinding.FragmentHomeBinding
import com.example.byd_sushi.home.pertemuan_10.TenthActivity
import com.example.byd_sushi.home.pertemuan_3.ThirdActivity
import com.example.byd_sushi.home.pertemuan_4.FourthActivity
import com.example.byd_sushi.home.pertemuan_5.FifthActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val menuList = listOf(
        HomeMenuModel("project", "📁", "My Project", "Detail proyek"),
        HomeMenuModel("dashboard", "📊", "Dashboard", "Laporan penjualan"),
        HomeMenuModel("third", "✉️", "Kirim Kode", "Pertemuan 3"),
        HomeMenuModel("fourth", "🏫", "Ke Fourth", "Pertemuan 4"),
        HomeMenuModel("fifth", "🧾", "Ke Fifth", "Pertemuan 5"),
        HomeMenuModel("tenth", "🗂️", "Tab & Produk", "Pertemuan 10")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Hebepku"

        val menuAdapter = HomeMenuAdapter(menuList) { menu ->
            when (menu.id) {
                "project" -> open(DetailActivity::class.java)
                "dashboard" -> open(DashboardActivity::class.java)
                "third" -> open(ThirdActivity::class.java)
                "fourth" -> {
                    val intent = Intent(requireContext(), FourthActivity::class.java)
                    intent.putExtra("name", "Politeknik Caltex Riau")
                    intent.putExtra("from", "Rumbai")
                    intent.putExtra("age", 25)
                    startActivity(intent)
                }
                "fifth" -> open(FifthActivity::class.java)
                "tenth" -> open(TenthActivity::class.java)
            }
        }

        binding.rvHomeMenu.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = menuAdapter
        }
    }

    private fun open(target: Class<*>) {
        startActivity(Intent(requireContext(), target))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}