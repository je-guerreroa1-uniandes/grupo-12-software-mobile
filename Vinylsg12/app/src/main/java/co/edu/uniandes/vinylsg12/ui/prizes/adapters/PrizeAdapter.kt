package co.edu.uniandes.vinylsg12.ui.prizes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.common.api.models.Prize
import co.edu.uniandes.vinylsg12.databinding.ItemPrizeBinding

class PrizeAdapter(public var prizes: List<Prize>): RecyclerView.Adapter<PrizeAdapter.PrizeViewHolder>() {
    override fun getItemCount(): Int = prizes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrizeViewHolder {
        val binding = ItemPrizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PrizeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PrizeViewHolder, position: Int) {
        val prize = prizes[position]
        holder.bind(prize)
    }

    class PrizeViewHolder(private val binding: ItemPrizeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(prize: Prize) {
            binding.name.text = prize.name
            binding.organization.text = prize.organization
        }
    }
}