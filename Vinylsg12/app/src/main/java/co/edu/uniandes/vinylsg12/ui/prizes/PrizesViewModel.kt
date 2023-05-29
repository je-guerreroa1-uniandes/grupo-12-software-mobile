package co.edu.uniandes.vinylsg12.ui.prizes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinylsg12.common.api.interfaces.PrizeService
import co.edu.uniandes.vinylsg12.common.api.models.Prize
import co.edu.uniandes.vinylsg12.common.api.services.RetrofitPrizeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrizesViewModel: ViewModel() {

    private val service: PrizeService = RetrofitPrizeService()

    private val _prizes = MutableLiveData<List<Prize>>()
    val prizes: LiveData<List<Prize>> = _prizes

    fun fetchPrizes() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    service.prizes(
                        onComplete = {
                            _prizes.postValue(it)
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