package co.edu.uniandes.vinylsg12.ui.musicians

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.common.api.models.Album
import co.edu.uniandes.vinylsg12.common.api.models.Musician
import co.edu.uniandes.vinylsg12.databinding.FragmentMusiciansBinding
import co.edu.uniandes.vinylsg12.ui.musicians.adapters.MusicianAdapter

class MusiciansFragment: Fragment() {

    private var _binding: FragmentMusiciansBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val musiciansViewModel =
            ViewModelProvider(this).get(MusiciansViewModel::class.java)

        _binding = FragmentMusiciansBinding.inflate(inflater, container, false)
        val root: View = binding.root

        subscribeToViewModel(musiciansViewModel)
        musiciansViewModel.fetchData()

        return root
    }

    private fun subscribeToViewModel(musiciansViewModel: MusiciansViewModel) {
        val recyclerView: RecyclerView = binding.musiciansRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = MusicianAdapter(listOf())
        recyclerView.adapter = adapter
        musiciansViewModel.musicians.observe(viewLifecycleOwner) {
            adapter.musicians = it
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