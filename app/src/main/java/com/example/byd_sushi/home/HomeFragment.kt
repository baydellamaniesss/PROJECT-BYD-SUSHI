
package com.example.byd_sushi.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.byd_sushi.DetailActivity
import com.example.byd_sushi.DashboardActivity
import com.example.byd_sushi.databinding.FragmentHomeBinding
import com.example.byd_sushi.home.pertemuan_3.ThirdActivity
import com.example.byd_sushi.home.pertemuan_4.FourthActivity
import com.example.byd_sushi.home.pertemuan_5.FifthActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity)
            .setSupportActionBar(binding.toolbar)

        (requireActivity() as AppCompatActivity)
            .supportActionBar?.title = "Sushi"

        // Tombol My Project
        binding.btnMyProject.setOnClickListener {
            startActivity(
                Intent(requireContext(), DetailActivity::class.java)
            )
        }

        // Tombol Dashboard
        binding.btnDashboard.setOnClickListener {
            startActivity(
                Intent(requireContext(), DashboardActivity::class.java)
            )
        }

        // Tombol Kirim Kode
        binding.btnKirimKode.setOnClickListener {
            startActivity(
                Intent(requireContext(), ThirdActivity::class.java)
            )
        }

        // Tombol To Fourth
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(
                requireContext(),
                FourthActivity::class.java
            )

            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)

            startActivity(intent)
        }

        // Tombol To Fifth
        binding.btnToFifth.setOnClickListener {
            startActivity(
                Intent(requireContext(), FifthActivity::class.java)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
