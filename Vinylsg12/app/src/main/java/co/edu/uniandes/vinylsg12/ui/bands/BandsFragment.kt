package co.edu.uniandes.vinylsg12.ui.bands

import android.animation.ObjectAnimator
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.R
import co.edu.uniandes.vinylsg12.databinding.FragmentMusiciansBinding
import co.edu.uniandes.vinylsg12.ui.bands.adapters.BandAdapter
import co.edu.uniandes.vinylsg12.ui.musicians.MusiciansViewModel
import co.edu.uniandes.vinylsg12.ui.musicians.adapters.MusicianAdapter

class BandsFragment : Fragment() {

    private var _binding: FragmentMusiciansBinding? = null
    private lateinit var viewModel: BandsViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(BandsViewModel::class.java)

        _binding = FragmentMusiciansBinding.inflate(inflater, container, false)

        val root: View = binding.root

        subscribeToViewModel(viewModel)
        viewModel.fetchData()

        return root
    }

    private fun subscribeToViewModel(viewModel: BandsViewModel) {
        val recyclerView: RecyclerView = binding.musiciansRecyclerView
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}