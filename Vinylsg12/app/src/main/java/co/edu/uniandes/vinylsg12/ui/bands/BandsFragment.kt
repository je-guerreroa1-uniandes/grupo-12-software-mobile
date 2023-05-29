package co.edu.uniandes.vinylsg12.ui.bands

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.R
import co.edu.uniandes.vinylsg12.databinding.FragmentBandsBinding
import co.edu.uniandes.vinylsg12.databinding.FragmentMusiciansBinding
import co.edu.uniandes.vinylsg12.ui.album.AlbumActivity
import co.edu.uniandes.vinylsg12.ui.albums.AlbumsViewModel
import co.edu.uniandes.vinylsg12.ui.albums.adapters.AlbumAdapter
import co.edu.uniandes.vinylsg12.ui.band.BandActivity
import co.edu.uniandes.vinylsg12.ui.bands.adapters.BandAdapter
import co.edu.uniandes.vinylsg12.ui.musicians.MusiciansViewModel
import co.edu.uniandes.vinylsg12.ui.musicians.adapters.MusicianAdapter

class BandsFragment : Fragment() {

    private var _binding: FragmentBandsBinding? = null
    private lateinit var viewModel: BandsViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(BandsViewModel::class.java)

        _binding = FragmentBandsBinding.inflate(inflater, container, false)

        val root: View = binding.root

        subscribeToViewModel(viewModel)
        viewModel.fetchData()

        return root
    }

    private fun subscribeToViewModel(viewModel: BandsViewModel) {
        val recyclerView: RecyclerView = binding.bandsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = BandAdapter(listOf())
        recyclerView.adapter = adapter
        viewModel.bands.observe(viewLifecycleOwner) {
            adapter.bands = it
            adapter.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE
        }
        binding.progressBar.visibility = View.VISIBLE
        val anim = ObjectAnimator.ofInt(binding.progressBar, "progress", 0, 100)
        anim.duration = 2000 // 2 seconds
        anim.start()
    }

    fun showBandActivity(context: Context, bandId: Int) {
        val intent = Intent(context, BandActivity::class.java).apply {
            putExtra(BandActivity.EXTRA_BAND_ID, bandId)
        }
        context.startActivity(intent)
    }

    fun onBindViewHolder(holder: BandAdapter.BandViewHolder, position: Int) {
        val band = viewModel.bands.value?.get(position) // get album at specified position
        band?.let {
            holder.bind(it)
            holder.itemView.setOnClickListener {
                showBandActivity(requireContext(), it.id)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}