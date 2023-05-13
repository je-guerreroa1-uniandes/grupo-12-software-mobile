package co.edu.uniandes.vinylsg12.ui.albums

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.databinding.FragmentAlbumsBinding
import co.edu.uniandes.vinylsg12.ui.album.AlbumActivity
import co.edu.uniandes.vinylsg12.ui.albums.adapters.AlbumAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentAlbumsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentAlbumsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        subscribeToVariables(homeViewModel)
        homeViewModel.fetchData()
        return root
    }

    private fun subscribeToVariables(homeViewModel: HomeViewModel) {
        val recyclerView: RecyclerView = binding.albumsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = AlbumAdapter(listOf())
        recyclerView.adapter = adapter
        homeViewModel.albums.observe(viewLifecycleOwner) {
            adapter.albums = it
            adapter.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE
        }
        binding.progressBar.visibility = View.VISIBLE
        val anim = ObjectAnimator.ofInt(binding.progressBar, "progress", 0, 100)
        anim.duration = 2000 // 2 seconds
        anim.start()
    }

    fun startAlbumActivity(context: Context, albumId: Int) {
        val intent = Intent(context, AlbumActivity::class.java).apply {
            putExtra(AlbumActivity.EXTRA_ALBUM_ID, albumId)
        }
        context.startActivity(intent)
    }

    fun onBindViewHolder(holder: AlbumAdapter.AlbumViewHolder, position: Int) {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val album = homeViewModel.albums.value?.get(position) // get album at specified position
        album?.let {
            holder.bind(it)
            holder.itemView.setOnClickListener {
                startAlbumActivity(requireContext(), it.id)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}