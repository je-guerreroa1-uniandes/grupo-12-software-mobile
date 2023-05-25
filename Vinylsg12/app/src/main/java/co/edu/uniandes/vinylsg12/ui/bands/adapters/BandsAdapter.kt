package co.edu.uniandes.vinylsg12.ui.bands.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.common.api.models.Band
import com.bumptech.glide.Glide

class BandsAdapter(public var band: List<Band>): RecyclerView.Adapter<BandsAdapter.BandViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BandViewHolder {
        val binding = ItemBandBinding.inflate//inflate(LayoutInflater.from(parent.context), parent, false)
        return BandViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BandViewHolder, position: Int) {
        val band = band[position]
        holder.bind(band)
    }

    override fun getItemCount(): Int = band.size

    class BandViewHolder(private val binding: ItemBandBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(band: Band) {
            binding.name.text = band.name
            Glide.with(binding.image)
                .load(band.image)
                .into(binding.image)
        }
    }

}