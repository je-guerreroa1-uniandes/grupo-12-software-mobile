package co.edu.uniandes.vinylsg12.ui.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import co.edu.uniandes.vinylsg12.R
import co.edu.uniandes.vinylsg12.databinding.ActivityAlbumFormBinding

class AlbumFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlbumFormBinding
    private lateinit var viewModel: AlbumFormViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlbumFormViewModel::class.java)
        binding = ActivityAlbumFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveActionButton.setOnClickListener {
            saveAlbum()
        }
    }

    private fun saveAlbum() {
        Toast.makeText(this, "Attempt to create an album", Toast.LENGTH_SHORT).show()// TODO: Remove this
        var name: String = binding.name.text.toString()
        var cover: String = binding.cover.text.toString()
        var releaseDate: String = binding.releaseDate.text.toString()
        var description: String = binding.description.text.toString()
        var genre: String = binding.genre.text.toString()
        var recordLabel: String = binding.recordLabel.text.toString()
        if (name.isNotEmpty() && cover.isNotEmpty() && releaseDate.isNotEmpty() && description.isNotEmpty() && genre.isNotEmpty() && recordLabel.isNotEmpty()) {
            viewModel.save(
                name,
                cover,
                releaseDate,
                description,
                genre,
                recordLabel,
                onSuccess = {
                            // TODO: To be implemented
                    Toast.makeText(this, "Album created", Toast.LENGTH_SHORT).show()// TODO: incomplete form
                    // TODO: genre selector {"statusCode":400,"message":"ValidationError: \"genre\" must be one of [Classical, Salsa, Rock, Folk]"}
                },
                onError = {
                    // TODO: Error -> To be implemented
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()// TODO: incomplete form
                }
            )
        } else {
            Toast.makeText(this, "Incomplete information", Toast.LENGTH_SHORT).show()// TODO: incomplete form
        }
    }
}