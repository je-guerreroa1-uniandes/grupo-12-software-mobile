package co.edu.uniandes.vinylsg12.ui.album

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinylsg12.common.api.interfaces.AlbumService
import co.edu.uniandes.vinylsg12.common.api.models.Album
import co.edu.uniandes.vinylsg12.common.api.services.RetrofitAlbumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumFormViewModel: ViewModel() {

    private val service: AlbumService = RetrofitAlbumService()

    fun save(
        name: String,
        cover: String,
        releaseDate: String,
        description: String,
        genre: String,
        recordLabel: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    service.create(name, cover, releaseDate, description, genre, recordLabel,
                        onComplete = {
                            onSuccess()
                        },
                        onError = {
                            Log.e("MyTag", "Error fetching albums: ${it.message}", it)
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