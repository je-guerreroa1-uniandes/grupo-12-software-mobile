package co.edu.uniandes.vinylsg12.ui.albums

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinylsg12.common.api.interfaces.AlbumService
import co.edu.uniandes.vinylsg12.common.api.models.Album
import co.edu.uniandes.vinylsg12.common.api.models.Collector
import co.edu.uniandes.vinylsg12.common.api.services.RetrofitAlbumService
import co.edu.uniandes.vinylsg12.common.session.UserSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumsViewModel : ViewModel() {

    private val service: AlbumService = RetrofitAlbumService()
    private val collectorUser: Collector? = UserSession.collector

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    private val _isLogged = MutableLiveData<Boolean>()
    val isLogged: LiveData<Boolean> = _isLogged

    public fun fetchData() {
        _isLogged.postValue(collectorUser != null)

        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    service.albums(
                        onComplete = { fetchedAlbums ->
                            _albums.postValue(fetchedAlbums)
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