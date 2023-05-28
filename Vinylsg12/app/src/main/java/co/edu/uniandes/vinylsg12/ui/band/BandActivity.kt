package co.edu.uniandes.vinylsg12.ui.band

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import co.edu.uniandes.vinylsg12.R
import co.edu.uniandes.vinylsg12.databinding.ActivityBandBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class BandActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBandBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel= ViewModelProvider(this).get(BandViewModel::class.java)
        binding = ActivityBandBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bandId = intent.getIntExtra(EXTRA_BAND_ID, -1)

        viewModel.band.observe(this) {
            val band = it ?: return@observe
            binding.name.text = band.name
            binding.description.text = band.description
            binding.creationDate.text = band.creationDate
            Glide.with(binding.image)
                .load(band.image)
                .transition(DrawableTransitionOptions.withCrossFade(2))
                .into(binding.image)
        }
        viewModel.fetchBand(bandId)
    }

    companion object {
        const val EXTRA_BAND_ID = "extra_band_id"
    }
}