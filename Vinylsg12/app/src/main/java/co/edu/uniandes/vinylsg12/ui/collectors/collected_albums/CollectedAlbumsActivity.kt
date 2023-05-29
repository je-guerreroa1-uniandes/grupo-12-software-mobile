package co.edu.uniandes.vinylsg12.ui.collectors.collected_albums

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.common.api.models.Album
import co.edu.uniandes.vinylsg12.databinding.ActivityCollectedAlbumsBinding
import co.edu.uniandes.vinylsg12.databinding.DialogAddToCollectionBinding
import co.edu.uniandes.vinylsg12.ui.collectors.collected_albums.adapters.CollectedAlbumsAdapter

class CollectedAlbumsActivity : AppCompatActivity() {

    private var _binding: ActivityCollectedAlbumsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CollectedAlbumsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(CollectedAlbumsViewModel::class.java)
        _binding = ActivityCollectedAlbumsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addAlbumButton.setOnClickListener {
            viewModel.fetchAlbums()
        }
        subscribeToVariables(viewModel)
        viewModel.fetchCollectedAlbums()
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CollectedAlbumsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchCollectedAlbums()
    }

    private fun subscribeToVariables(viewModel: CollectedAlbumsViewModel) {
        val recyclerView: RecyclerView = binding.albumsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CollectedAlbumsAdapter(listOf())
        recyclerView.adapter = adapter
        viewModel.collectedAlbums.observe(this) {
            adapter.collectedAlbums = it
            adapter.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE
        }
        viewModel.albums.observe(this) {
            showAddAlbumDialog(it)
        }
    }

    private fun showAddAlbumDialog(albums: List<Album>) {
        val dialogBinding = DialogAddToCollectionBinding.inflate(layoutInflater)
        val dialogView = dialogBinding.root
        // Inside your activity or fragment

// Create an AlertDialog.Builder object
        val builder = AlertDialog.Builder(this)
            .setTitle("Add Album to Collection")
            .setView(dialogView)

        val spinnerAlbum = dialogBinding.spinnerAlbum
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, albums.map { it.name })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAlbum.adapter = adapter

// Set a positive button click listener
        builder.setPositiveButton("Add") { dialog, _ ->
            // Get the price value from the input field and convert it to Int
            val priceString = dialogBinding.price.text.toString()
            val price = priceString.toIntOrNull()
            // Retrieve the selected album
            val selectedAlbum = albums[spinnerAlbum.selectedItemPosition]

            if (price != null) {
                // Add the selected album to your collection or perform any desired operations
                viewModel.addToCollection(
                    albumId = selectedAlbum.id ?: 0,
                    price = price,
                    onSuccess = {
                        // Dismiss the dialog
                        dialog.dismiss()
                        viewModel.fetchCollectedAlbums()
                    }
                ) {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Invalid price format: $priceString", Toast.LENGTH_LONG).show()
            }
        }

        // Create and show the dialog
        val dialog = builder.create()
        dialog.show()

    }
}