package co.edu.uniandes.vinylsg12.ui.prize

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinylsg12.common.api.interfaces.PrizeService
import co.edu.uniandes.vinylsg12.common.api.services.RetrofitPrizeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrizeFormViewModel: ViewModel() {

    private val service: PrizeService = RetrofitPrizeService()

    fun save(
        name: String,
        description: String,
        organization: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    service.create(name, description, organization,
                        onComplete = {
                            onSuccess()
                        },
                        onError = {
                            Log.e("MyTag", "Error saving album: ${it.message}", it)
                            onError(it)
                        }
                    )
                }
            } catch (e: Exception) {
                Log.e("MyTag", "Error saving album: ${e.message}", e)
            }
        }
    }
}