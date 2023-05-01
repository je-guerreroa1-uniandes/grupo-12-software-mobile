package co.edu.uniandes.vinylsg12.ui.home.adapters

import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.common.api.models.Album
import android.view.LayoutInflater
import android.view.ViewGroup
import co.edu.uniandes.vinylsg12.databinding.ItemAlbumBinding
import com.bumptech.glide.Glide

class AlbumAdapter(public var albums: List<Album>): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albums[position]
        holder.bind(album)
    }

    override fun getItemCount(): Int = albums.size

    class AlbumViewHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album) {
            binding.albumTitle.text = album.name
            binding.albumReleaseDate.text = album.releaseDate
            binding.albumGenre.text = album.genre
            Glide.with(binding.albumImage)
                .load(album.cover)
                .into(binding.albumImage)
        }
    }
}