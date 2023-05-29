package co.edu.uniandes.vinylsg12.ui.album

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import co.edu.uniandes.vinylsg12.databinding.ActivityAlbumFormBinding
import java.text.SimpleDateFormat
import java.util.*


class AlbumFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlbumFormBinding
    private lateinit var viewModel: AlbumFormViewModel
    private var selectedGenre: String = ""
    private var selectedRecordLabel: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlbumFormViewModel::class.java)
        binding = ActivityAlbumFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveActionButton.setOnClickListener {
            saveAlbum()
        }

        // Bind the Spinners
        val musicGenres = resources.getStringArray(co.edu.uniandes.vinylsg12.R.array.music_genres)
        val genreAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, musicGenres)
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.genreSpinner.adapter = genreAdapter

        val recordLabels = resources.getStringArray(co.edu.uniandes.vinylsg12.R.array.record_labels)
        val recordLabelsAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, recordLabels)
        recordLabelsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.recordLabelSpinner.adapter = recordLabelsAdapter

        binding.genreSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Do something with the selected music genre
                selectedGenre = parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        })

        binding.recordLabelSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedRecordLabel = parent.getItemAtPosition(position) as String
                // Do something with the selected music genre
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        })
    }

    private fun saveAlbum() {
        Toast.makeText(this, "Attempt to create an album", Toast.LENGTH_SHORT).show()// TODO: Remove this
        var name: String = binding.name.text.toString()
        var cover: String = binding.cover.text.toString()
        var releaseDate: String = getFormattedDateFromDatePicker(binding.releaseDatePicker)
        var description: String = binding.description.text.toString()
        if (name.isNotEmpty() && cover.isNotEmpty() && releaseDate.isNotEmpty() && description.isNotEmpty() && selectedGenre.isNotEmpty() && selectedRecordLabel.isNotEmpty()) {
            viewModel.save(
                name,
                cover,
                releaseDate,
                description,
                selectedGenre,
                selectedRecordLabel,
                onSuccess = {
                    Toast.makeText(this, "Album created", Toast.LENGTH_SHORT).show()// TODO: incomplete form
                    finish()
                },
                onError = {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()// TODO: incomplete form
                }
            )
        } else {
            Toast.makeText(this, "Incomplete information", Toast.LENGTH_SHORT).show()// TODO: incomplete form
        }
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