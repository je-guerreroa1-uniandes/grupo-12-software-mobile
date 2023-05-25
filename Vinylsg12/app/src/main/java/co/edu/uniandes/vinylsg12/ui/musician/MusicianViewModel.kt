package co.edu.uniandes.vinylsg12.ui.musician

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinylsg12.common.api.interfaces.MusicianService
import co.edu.uniandes.vinylsg12.common.api.models.Musician
import co.edu.uniandes.vinylsg12.common.api.services.RetrofitMusicianService
import kotlinx.coroutines.launch

class MusicianViewModel : ViewModel() {

    private val service: MusicianService = RetrofitMusicianService()

    private val _text = MutableLiveData<String>().apply {
        value = "Musician name"
    }
    val text: LiveData<String> = _text

    private val _musician = MutableLiveData<Musician>().apply {
        value = null
    }
    val musician: LiveData<Musician?> = _musician

    public fun fetchMusician(musicianId: Int) {
        viewModelScope.launch {
            try {
                service.musicians(musicianId,
                    onComplete = { musician ->
                        _musician.postValue(musician)
                    },
                    onError = { error ->
                        Log.e("MyTag", "Error fetching musician: ${error.message}", error)
                    }
                )
            } catch (e: Exception) {
                Log.e("MyTag", "Error fetching musician: ${e.message}", e)
            }
        }
    }
}