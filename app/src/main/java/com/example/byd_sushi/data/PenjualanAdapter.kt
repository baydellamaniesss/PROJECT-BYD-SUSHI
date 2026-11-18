package com.example.byd_sushi.data

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.byd_sushi.databinding.ItemPenjualanBinding

class PenjualanAdapter(
    private var items: List<PenjualanModel>,
    private val onItemClick: (PenjualanModel) -> Unit
) : RecyclerView.Adapter<PenjualanAdapter.PenjualanViewHolder>() {

    inner class PenjualanViewHolder(val binding: ItemPenjualanBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenjualanViewHolder {
        val binding = ItemPenjualanBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PenjualanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PenjualanViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            tvProduk.text = item.produk
            tvInfo.text = "${item.tanggal} · ${item.jumlah} pcs"   // sebelumnya tvTanggal
            tvHarga.text = item.total                               // sebelumnya tvTotal
            tvStatus.text = item.status
            tvStatus.setTextColor(
                if (item.status == "Selesai") Color.parseColor("#1D9E75")
                else Color.parseColor("#8A5A00")
            )
            root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount(): Int = items.size

    // Dipakai untuk pencarian dan filter
    fun updateData(newItems: List<PenjualanModel>) {
        items = newItems
        notifyDataSetChanged()
    }
}