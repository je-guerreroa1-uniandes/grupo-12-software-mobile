package co.edu.uniandes.vinylsg12.ui.bands.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.common.api.models.Musician
import co.edu.uniandes.vinylsg12.databinding.ItemBandMusicianBinding
import co.edu.uniandes.vinylsg12.databinding.ItemMusicianBinding
import co.edu.uniandes.vinylsg12.ui.musicians.adapters.MusicianAdapter
import com.bumptech.glide.Glide

class BandMusiciansAdapter(public var musicians: List<Musician>): RecyclerView.Adapter<BandMusiciansAdapter.BandMusicianViewHolder>() {
    private var itemWidth: Int = 0

    override fun getItemCount(): Int = musicians.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BandMusicianViewHolder {
        if (itemWidth == 0) {
            val displayMetrics = parent.context.resources.displayMetrics
            val screenWidth = displayMetrics.widthPixels
            itemWidth = screenWidth / 3
        }

        val binding = ItemBandMusicianBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val layoutParams = binding.root.layoutParams
        layoutParams.width = itemWidth
        binding.root.layoutParams = layoutParams
        return BandMusicianViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BandMusicianViewHolder, position: Int) {
        val musician = musicians[position]
        holder.bind(musician)
    }

    class BandMusicianViewHolder(private val binding: ItemBandMusicianBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(musician: Musician) {
            binding.name.text = musician.name
            Glide.with(binding.image)
                .load(musician.image)
                .into(binding.image)
        }
    }
}