package co.edu.uniandes.vinylsg12.ui.musician.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.common.api.models.AwardedPerformer
import co.edu.uniandes.vinylsg12.databinding.ItemAwardPrizeBinding

class AwardedPerformerAdapter(public var awardPerformers: List<AwardedPerformer>): RecyclerView.Adapter<AwardedPerformerAdapter.AwardedPerformerViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AwardedPerformerViewHolder {
        val binding = ItemAwardPrizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AwardedPerformerViewHolder(binding)
    }

    override fun getItemCount(): Int = awardPerformers.size

    override fun onBindViewHolder(holder: AwardedPerformerViewHolder, position: Int) {
        val musician = awardPerformers[position]
        holder.bind(musician)
    }

    class AwardedPerformerViewHolder(private val binding: ItemAwardPrizeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(awardPerformer: AwardedPerformer) {
            binding.awardDate.text = awardPerformer.premiationDate
        }
    }
}