package co.edu.uniandes.vinylsg12.ui.collectors.collected_albums

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.databinding.ActivityCollectedAlbumsBinding
import co.edu.uniandes.vinylsg12.ui.albums.adapters.AlbumAdapter
import co.edu.uniandes.vinylsg12.ui.collectors.collected_albums.adapters.CollectedAlbumsAdapter

class CollectedAlbumsActivity : AppCompatActivity() {

    private var _binding: ActivityCollectedAlbumsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel =
            ViewModelProvider(this).get(CollectedAlbumsViewModel::class.java)
        _binding = ActivityCollectedAlbumsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeToVariables(viewModel)
        viewModel.fetchData()
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CollectedAlbumsActivity::class.java)
            context.startActivity(intent)
        }
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
    }
}