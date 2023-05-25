package co.edu.uniandes.vinylsg12.ui.musician

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import co.edu.uniandes.vinylsg12.databinding.ActivityMusicianBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


class MusicianActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMusicianBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val musicianViewModel =
            ViewModelProvider(this).get(MusicianViewModel::class.java)
        binding = ActivityMusicianBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val musicianId = intent.getIntExtra(EXTRA_MUSICIAN_ID, -1)
        // do something with the musicianId

        musicianViewModel.musician.observe(this) {
            val musician = it ?: return@observe
            //binding.muscisianName.text = musician.name
            //binding.musicianImagen.text = musician.image
            //binding.musicianDescription.text = musician.description
            //binding.musicicianBirthDate.text = musician.birthDate
            //Glide.with(binding.musicianImage)
              //  .into(binding.musicianImage)
        }

        musicianViewModel.fetchMusician(musicianId)
    }

    companion object {
        const val EXTRA_MUSICIAN_ID = "extra_MUSICIAN_id"
    }
}
