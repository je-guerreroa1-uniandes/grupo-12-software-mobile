package co.edu.uniandes.vinylsg12.ui.bands

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinylsg12.common.api.interfaces.BandService
import co.edu.uniandes.vinylsg12.common.api.models.Band
import co.edu.uniandes.vinylsg12.common.api.services.RetrofitBandService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class BandsViewModel : ViewModel() {
    private var service: BandService = RetrofitBandService()

    private val _bands = MutableLiveData<List<Band>>()
    val bands: LiveData<List<Band>> = _bands

    public fun fetchData() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    service.bands(
                        onComplete = { fetchedBands ->
                            _bands.postValue(fetchedBands)
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