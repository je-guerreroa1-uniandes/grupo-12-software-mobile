package co.edu.uniandes.vinylsg12.ui.album

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import co.edu.uniandes.vinylsg12.databinding.ActivityAlbumBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class AlbumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val albumViewModel =
            ViewModelProvider(this).get(AlbumViewModel::class.java)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val albumId = intent.getIntExtra(EXTRA_ALBUM_ID, -1)
        // do something with the albumId

        albumViewModel.album.observe(this) {
            val album = it ?: return@observe
            binding.albumTitle.text = album.name
            binding.albumReleaseDate.text = album.releaseDate
            binding.albumGenre.text = album.genre
            binding.albumDescription.text = album.description
            binding.albumRecordLabel.text = album.recordLabel
            Glide.with(binding.albumImage)
                .load(album.cover)
                .transition(DrawableTransitionOptions.withCrossFade(2))
                .into(binding.albumImage)
        }

        albumViewModel.fetchAlbum(albumId)
    }

    companion object {
        const val EXTRA_ALBUM_ID = "extra_album_id"
    }
}