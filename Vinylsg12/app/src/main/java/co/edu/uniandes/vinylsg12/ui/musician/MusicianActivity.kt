package co.edu.uniandes.vinylsg12.ui.musician

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinylsg12.common.api.models.Prize
import co.edu.uniandes.vinylsg12.databinding.ActivityMusicianBinding
import co.edu.uniandes.vinylsg12.databinding.DialogAddPrizeToMusicianBinding
import co.edu.uniandes.vinylsg12.ui.albums.adapters.AlbumAdapter
import co.edu.uniandes.vinylsg12.ui.band.BandActivity
import co.edu.uniandes.vinylsg12.ui.musician.adapters.AwardedPerformerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MusicianActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMusicianBinding
    private lateinit var viewModel: MusicianViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= ViewModelProvider(this).get(MusicianViewModel::class.java)
        binding = ActivityMusicianBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeToVariables()

        val musicianId = intent.getIntExtra(BandActivity.EXTRA_BAND_ID, -1)
        viewModel.fetchMusician(musicianId)

        binding.addPrizeButton.setOnClickListener {
            viewModel.fetchPrizes()
        }
    }

    private fun subscribeToVariables() {
        val recyclerView: RecyclerView = binding.prizesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AwardedPerformerAdapter(listOf())
        recyclerView.adapter = adapter

        viewModel.musician.observe(this) { musician ->
            musician?.let {
                binding.name.text = musician.name
                binding.description.text = musician.description
                binding.birthdate.text = "Birthdate: ${musician.birthDate}"
                Glide.with(binding.image)
                    .load(musician.image)
                    .transition(DrawableTransitionOptions.withCrossFade(2))
                    .into(binding.image)
                adapter.awardPerformers = musician.performerPrizes
                adapter.notifyDataSetChanged()
            }
        }

        viewModel.prizes.observe(this) { prizes ->
            if (prizes.isNotEmpty()) {
                showAddPrizeDialog(prizes)
            }
        }
    }

    companion object {
        const val EXTRA_BAND_ID = "extra_band_id"

        fun start(context: Context) {
            val intent = Intent(context, MusicianActivity::class.java)
            context.startActivity(intent)
        }
    }

    private fun showAddPrizeDialog(prizes: List<Prize>) {
        val dialogBinding = DialogAddPrizeToMusicianBinding.inflate(layoutInflater)
        val dialogView = dialogBinding.root
        // Inside your activity or fragment

// Create an AlertDialog.Builder object
        val builder = AlertDialog.Builder(this)
            .setTitle("Add prize to Band")
            .setView(dialogView)

        val spinnerPrize = dialogBinding.spinnerPrize
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, prizes.map { it.name })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPrize.adapter = adapter

        // Set a positive button click listener
        builder.setPositiveButton("Add") { dialog, _ ->
            val selectedPrize = prizes[spinnerPrize.selectedItemPosition].id ?: 0
            var awardDate: String = getFormattedDateFromDatePicker(dialogBinding.awardDatePicker)

            viewModel.addPrize(
                musicianId = viewModel.musician.value?.id ?: 0,
                prizeId = selectedPrize,
                awardDate = awardDate,
                onSuccess = {
                    dialog.dismiss()
                    viewModel.fetchMusician(viewModel.musician.value?.id!!)
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

    private fun getFormattedDateFromDatePicker(datePicker: DatePicker): String {
        val day: Int = datePicker.dayOfMonth
        val month: Int = datePicker.month
        val year: Int = datePicker.year

        // Format the date as desired (e.g., "yyyy-MM-dd")
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
}