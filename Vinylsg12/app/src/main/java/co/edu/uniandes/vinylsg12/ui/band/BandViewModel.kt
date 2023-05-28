package co.edu.uniandes.vinylsg12.ui.band

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinylsg12.common.api.interfaces.BandService
import co.edu.uniandes.vinylsg12.common.api.interfaces.MusicianService
import co.edu.uniandes.vinylsg12.common.api.models.Band
import co.edu.uniandes.vinylsg12.common.api.models.Musician
import co.edu.uniandes.vinylsg12.common.api.services.RetrofitBandService
import co.edu.uniandes.vinylsg12.common.api.services.RetrofitMusicianService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BandViewModel: ViewModel() {

    private val bandService: BandService = RetrofitBandService()
    private val musicianService: MusicianService = RetrofitMusicianService()

    private val _band = MutableLiveData<Band>().apply {
        value = null
    }
    val band: LiveData<Band?> = _band

    private val _musicians = MutableLiveData<List<Musician>>()
    val musicians: LiveData<List<Musician>> = _musicians

    fun fetchBand(bandId: Int) {
        viewModelScope.launch {
            try {
                bandService.band(bandId,
                    onComplete = {
                        _band.postValue(it)
                    },
                    onError = { error ->
                        Log.e("MyTag", "Error fetching album: ${error.message}", error)
                    }
                )
            } catch (e: Exception) {
                Log.e("MyTag", "Error fetching album: ${e.message}", e)
            }
        }
    }

    fun fetchMusicians() {
        viewModelScope.launch {
            try {
                musicianService.musicians(
                    onComplete = { musicians ->
                        val collectedIds = band.value?.musicians?.map { it.id } ?: emptyList()
                        val filteredMusicians = musicians.filter { it.id !in collectedIds }
                        _musicians.postValue(filteredMusicians)
                    },
                    onError = { error ->
                        Log.e("MyTag", "Error fetching albums: ${error.message}", error)
                    }
                )
            } catch (e: Exception) {
                Log.e("MyTag", "Error fetching albums: ${e.message}", e)
            }
        }
    }

    fun addToBand(musicianId: Int, bandId: Int, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    bandService.add(
                        musicianId = musicianId,
                        bandId = bandId,
                        onComplete = {
                            onSuccess()
                        },
                        onError = {
                            onError(it)
                        }
                    )
                }
            } catch (e: Exception) {
                Log.e("MyTag", "Error fetching albums: ${e.message}", e)
            }
        }
    }
}