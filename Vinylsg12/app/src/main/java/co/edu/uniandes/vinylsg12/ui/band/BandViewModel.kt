package co.edu.uniandes.vinylsg12.ui.band

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinylsg12.common.api.interfaces.BandService
import co.edu.uniandes.vinylsg12.common.api.models.Band
import co.edu.uniandes.vinylsg12.common.api.services.RetrofitBandService
import kotlinx.coroutines.launch
import java.lang.Exception

class BandViewModel: ViewModel() {

    private val service: BandService = RetrofitBandService()

    private val _band = MutableLiveData<Band>().apply {
        value = null
    }
    val band: LiveData<Band?> = _band

    fun fetchBand(bandId: Int) {
        viewModelScope.launch {
            try {
                service.band(bandId,
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
}