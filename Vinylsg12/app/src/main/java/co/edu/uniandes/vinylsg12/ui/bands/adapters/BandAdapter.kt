package co.edu.uniandes.vinylsg12.ui.bands.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.common.api.models.Band
import co.edu.uniandes.vinylsg12.common.api.models.Musician
import co.edu.uniandes.vinylsg12.databinding.ItemBandBinding
import co.edu.uniandes.vinylsg12.databinding.ItemMusicianBinding
import com.bumptech.glide.Glide

class BandAdapter(var bands: List<Band>): RecyclerView.Adapter<BandAdapter.BandViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BandViewHolder {
        val binding = ItemBandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BandViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BandViewHolder, position: Int) {
        val band = bands[position]
        holder.bind(band)
    }

    override fun getItemCount(): Int = bands.size

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