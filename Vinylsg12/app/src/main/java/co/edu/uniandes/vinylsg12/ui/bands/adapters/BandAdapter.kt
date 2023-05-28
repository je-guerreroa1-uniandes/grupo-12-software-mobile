package co.edu.uniandes.vinylsg12.ui.bands.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.common.api.models.Band
import co.edu.uniandes.vinylsg12.common.api.models.Musician
import co.edu.uniandes.vinylsg12.databinding.ItemBandBinding
import co.edu.uniandes.vinylsg12.databinding.ItemMusicianBinding
import co.edu.uniandes.vinylsg12.ui.album.AlbumActivity
import co.edu.uniandes.vinylsg12.ui.band.BandActivity
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

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, BandActivity::class.java).apply {
                    putExtra(BandActivity.EXTRA_BAND_ID, band.id)
                }
                binding.root.context.startActivity(intent)
            }
        }
    }
}