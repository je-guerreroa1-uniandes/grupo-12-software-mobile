package co.edu.uniandes.vinylsg12.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinylsg12.common.api.interfaces.CollectorService
import co.edu.uniandes.vinylsg12.common.api.models.Collector
import co.edu.uniandes.vinylsg12.common.api.services.RetrofitCollectorService
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val service: CollectorService = RetrofitCollectorService()

    private val _collector = MutableLiveData<Collector>().apply {
        value = null
    }
    val collector: LiveData<Collector?> = _collector

    fun recoverCollector(
        id: Int,
        onError: (Throwable) -> Unit
    ) {
        viewModelScope.launch {
            try {
                service.collector(id,
                    onComplete = {
                        _collector.postValue(it)
                    },
                    onError = {
                        Log.e("MyTag", "Error fetching album: ${it.message}", it)
                        onError(it)
                    }
                )
            } catch (e: Exception) {
                Log.e("MyTag", "Error fetching collector: ${e.message}", e)
            }
        }
    }
}