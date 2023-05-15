package co.edu.uniandes.vinylsg12.ui.album

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import co.edu.uniandes.vinylsg12.databinding.ActivityAlbumFormBinding


class AlbumFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlbumFormBinding
    private lateinit var viewModel: AlbumFormViewModel
    private var selectedGenre: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlbumFormViewModel::class.java)
        binding = ActivityAlbumFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveActionButton.setOnClickListener {
            saveAlbum()
        }

        // Bind the Spinner
        val musicGenres = resources.getStringArray(co.edu.uniandes.vinylsg12.R.array.music_genres)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, musicGenres)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.genreSpinner.adapter = adapter

        binding.genreSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedGenre = parent.getItemAtPosition(position) as String
                // Do something with the selected music genre
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        })
    }

    private fun saveAlbum() {
        Toast.makeText(this, "Attempt to create an album", Toast.LENGTH_SHORT).show()// TODO: Remove this
        var name: String = binding.name.text.toString()
        var cover: String = binding.cover.text.toString()
        var releaseDate: String = binding.releaseDate.text.toString()
        var description: String = binding.description.text.toString()
        var recordLabel: String = binding.recordLabel.text.toString()
        if (name.isNotEmpty() && cover.isNotEmpty() && releaseDate.isNotEmpty() && description.isNotEmpty() && selectedGenre.isNotEmpty() && recordLabel.isNotEmpty()) {
            viewModel.save(
                name,
                cover,
                releaseDate,
                description,
                selectedGenre,
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