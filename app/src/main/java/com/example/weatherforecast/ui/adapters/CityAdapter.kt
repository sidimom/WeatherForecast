package com.example.weatherforecast.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.databinding.CityRvItemBinding
import com.example.weatherforecast.entities.CityItem

class CityAdapter: RecyclerView.Adapter<CityAdapter.Holder>() {

    private val items = mutableListOf<CityItem>()
    var listener: ((CityItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(
        CityRvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    fun setItems(items: List<CityItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class Holder(private val binding: CityRvItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(cityItem: CityItem) {
            binding.cityTv.text = cityItem.name
        }

        init{
            binding.cityTv.setOnClickListener {
                listener?.invoke(items[adapterPosition])
            }
        }
    }
}