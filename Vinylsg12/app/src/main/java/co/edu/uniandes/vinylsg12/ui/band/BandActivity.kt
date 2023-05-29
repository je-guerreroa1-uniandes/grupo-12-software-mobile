package co.edu.uniandes.vinylsg12.ui.band

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.common.api.models.Musician
import co.edu.uniandes.vinylsg12.databinding.ActivityBandBinding
import co.edu.uniandes.vinylsg12.databinding.DialogAddToBandBinding
import co.edu.uniandes.vinylsg12.ui.bands.adapters.BandMusiciansAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


class BandActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBandBinding
    private lateinit var viewModel: BandViewModel

    companion object {
        const val EXTRA_BAND_ID = "extra_band_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= ViewModelProvider(this).get(BandViewModel::class.java)
        binding = ActivityBandBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bandId = intent.getIntExtra(EXTRA_BAND_ID, -1)

        subscribeToVariables()

        binding.addMusicianButton.setOnClickListener {
            viewModel.fetchMusicians()
        }

        viewModel.fetchBand(bandId)
    }

    private fun subscribeToVariables() {
        val recyclerView: RecyclerView = binding.musiciansRecyclerView
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        val adapter = BandMusiciansAdapter(listOf())
        recyclerView.adapter = adapter

        viewModel.band.observe(this) { band ->
            band?.let {
                binding.name.text = band.name
                binding.description.text = band.description
                binding.creationDate.text = band.creationDate
                Glide.with(binding.image)
                    .load(band.image)
                    .transition(DrawableTransitionOptions.withCrossFade(2))
                    .into(binding.image)
                adapter.musicians = band.musicians
                adapter.notifyDataSetChanged()
            }
        }

        viewModel.musicians.observe(this) { musicians ->
            showAddMusicianDialog(musicians)
        }
    }

    private fun showAddMusicianDialog(musicians: List<Musician>) {
        val dialogBinding = DialogAddToBandBinding.inflate(layoutInflater)
        val dialogView = dialogBinding.root
        // Inside your activity or fragment

// Create an AlertDialog.Builder object
        val builder = AlertDialog.Builder(this)
            .setTitle("Add musician to Band")
            .setView(dialogView)

        val spinnerAlbum = dialogBinding.spinnerMusician
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, musicians.map { it.name })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAlbum.adapter = adapter

        // Set a positive button click listener
        builder.setPositiveButton("Add") { dialog, _ ->
            // Retrieve the selected musician
            val selectedMusician = musicians[spinnerAlbum.selectedItemPosition]
            // Add the selected album to your collection or perform any desired operations
            viewModel.addToBand(
                musicianId = selectedMusician.id ?: 0,
                bandId = viewModel.band.value?.id!!,
                onSuccess = {
                                dialog.dismiss()
                                viewModel.fetchBand(viewModel.band.value?.id!!)
                            },
                    onError = {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                )
        }

        // Create and show the dialog
        val dialog = builder.create()
        dialog.show()

    }
}