package co.edu.uniandes.vinylsg12.ui.musician

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinylsg12.common.api.interfaces.AwardedPerformerService
import co.edu.uniandes.vinylsg12.common.api.interfaces.MusicianService
import co.edu.uniandes.vinylsg12.common.api.interfaces.PrizeService
import co.edu.uniandes.vinylsg12.common.api.models.Musician
import co.edu.uniandes.vinylsg12.common.api.models.Prize
import co.edu.uniandes.vinylsg12.common.api.services.RetrofitAwardedPerformerService
import co.edu.uniandes.vinylsg12.common.api.services.RetrofitMusicianService
import co.edu.uniandes.vinylsg12.common.api.services.RetrofitPrizeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MusicianViewModel: ViewModel() {

    private val musicianService: MusicianService = RetrofitMusicianService()
    private val prizeService: PrizeService = RetrofitPrizeService()
    private val awardedPerformerService: AwardedPerformerService = RetrofitAwardedPerformerService()

    private val _musician = MutableLiveData<Musician>().apply {
        value = null
    }
    val musician: LiveData<Musician?> = _musician

    private val _prizes = MutableLiveData<List<Prize>>().apply {
        value = emptyList()
    }
    val prizes: LiveData<List<Prize>> = _prizes

    fun fetchMusician(musicianId: Int) {
        viewModelScope.launch {
            try {
                musicianService.musician(musicianId,
                    onComplete = {
                        _musician.postValue(it)
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

    fun fetchPrizes() {
        viewModelScope.launch {
            try {
                prizeService.prizes(
                    onComplete = { prizes ->
//                        val collectedIds = musician.value?.prizes?.map { it.id } ?: emptyList()
//                        val filteredMusicians = musicians.filter { it.id !in collectedIds }
                        _prizes.postValue(prizes)
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

    fun addPrize(musicianId: Int, prizeId: Int, awardDate: String, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    awardedPerformerService.add(musicianId, prizeId, awardDate,
                            onComplete = {
                                onSuccess()
                            },
                            onError = {
                                onError(it)
                            }
                        )
                }
            } catch (e: Exception) {
                Log.e("MyTag", "Error adding prize to musician: ${e.message}", e)
            }
        }
    }
}