package co.edu.uniandes.vinylsg12.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.databinding.FragmentHomeBinding
import co.edu.uniandes.vinylsg12.ui.home.adapters.AlbumAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}