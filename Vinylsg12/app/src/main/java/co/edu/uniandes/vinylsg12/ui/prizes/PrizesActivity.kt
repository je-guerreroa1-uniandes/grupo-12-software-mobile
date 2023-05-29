package co.edu.uniandes.vinylsg12.ui.prizes

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.R
import co.edu.uniandes.vinylsg12.databinding.ActivityPrizesBinding
import co.edu.uniandes.vinylsg12.ui.collectors.collected_albums.CollectedAlbumsActivity
import co.edu.uniandes.vinylsg12.ui.prizes.adapters.PrizeAdapter

class PrizesActivity : AppCompatActivity() {

    private var _binding: ActivityPrizesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PrizesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PrizesViewModel::class.java)
        _binding = ActivityPrizesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeToVariables()
        viewModel.fetchPrizes()
    }

    fun subscribeToVariables(){
        val recyclerView: RecyclerView = binding.prizesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PrizeAdapter(listOf())
        recyclerView.adapter = adapter
        viewModel.prizes.observe(this) {
            adapter.prizes = it
            adapter.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, PrizesActivity::class.java)
            context.startActivity(intent)
        }
    }
}