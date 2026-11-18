package com.example.byd_sushi.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.byd_sushi.databinding.ItemHomeMenuBinding

class HomeMenuAdapter(
    private val menuList: List<HomeMenuModel>,
    private val onItemClick: (HomeMenuModel) -> Unit
) : RecyclerView.Adapter<HomeMenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(val binding: ItemHomeMenuBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemHomeMenuBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = menuList[position]
        with(holder.binding) {
            tvEmoji.text = item.emoji
            tvTitle.text = item.title
            tvSubtitle.text = item.subtitle
            root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount(): Int = menuList.size
}