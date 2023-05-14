package co.edu.uniandes.vinylsg12.ui.musicians

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinylsg12.common.api.interfaces.MusicianService
import co.edu.uniandes.vinylsg12.common.api.models.Musician
import co.edu.uniandes.vinylsg12.common.api.services.RetrofitMusicianService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MusiciansViewModel : ViewModel() {

    private var service: MusicianService = RetrofitMusicianService()

    private val _musicians = MutableLiveData<List<Musician>>()
    val musicians: LiveData<List<Musician>> = _musicians

    public fun fetchData() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    service.musicians(
                        onComplete = { fetchedMusicians ->
                            _musicians.postValue(fetchedMusicians)
                        },
                        onError = { error ->
                            Log.e("MyTag", "Error fetching albums: ${error.message}", error)
                        }
                    )
                }
            } catch (e: Exception) {
                Log.e("MyTag", "Error fetching albums: ${e.message}", e)
            }
        }
    }
}