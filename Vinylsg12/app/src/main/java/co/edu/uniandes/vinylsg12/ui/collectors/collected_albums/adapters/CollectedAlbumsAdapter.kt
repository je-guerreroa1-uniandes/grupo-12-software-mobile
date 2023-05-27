package co.edu.uniandes.vinylsg12.ui.collectors.collected_albums.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.common.api.models.CollectedAlbum
import co.edu.uniandes.vinylsg12.databinding.ItemCollectedAlbumBinding
import co.edu.uniandes.vinylsg12.ui.album.AlbumActivity
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.*

class CollectedAlbumsAdapter(public var collectedAlbums: List<CollectedAlbum>): RecyclerView.Adapter<CollectedAlbumsAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemCollectedAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun getItemCount(): Int = collectedAlbums.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val collectedAlbum = collectedAlbums[position]
        holder.bind(collectedAlbum)
    }

    class AlbumViewHolder(private val binding: ItemCollectedAlbumBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(collectedAlbum: CollectedAlbum) {
            val album = collectedAlbum.album!!
            binding.albumTitle.text = album.name
            binding.albumGenre.text = album.genre
            // Format price with currency
            val priceFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
            val formattedPrice = priceFormat.format(collectedAlbum.price)
            binding.price.text = formattedPrice
            Glide.with(binding.albumImage)
                .load(album.cover)
                .into(binding.albumImage)

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, AlbumActivity::class.java).apply {
                    putExtra(AlbumActivity.EXTRA_ALBUM_ID, album.id)
                }
                binding.root.context.startActivity(intent)
            }
        }
    }
}