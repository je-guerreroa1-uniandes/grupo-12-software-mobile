package co.edu.uniandes.vinylsg12.ui.musicians.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.common.api.models.Musician
import co.edu.uniandes.vinylsg12.databinding.ItemMusicianBinding
import com.bumptech.glide.Glide

class MusicianAdapter(public var musicians: List<Musician>): RecyclerView.Adapter<MusicianAdapter.MusicianViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicianViewHolder {
        val binding = ItemMusicianBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicianViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicianViewHolder, position: Int) {
        val musician = musicians[position]
        holder.bind(musician)
    }

    override fun getItemCount(): Int = musicians.size

    class MusicianViewHolder(private val binding: ItemMusicianBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(musician: Musician) {
            binding.name.text = musician.name
            Glide.with(binding.image)
                .load(musician.image)
                .into(binding.image)
        }
    }

}