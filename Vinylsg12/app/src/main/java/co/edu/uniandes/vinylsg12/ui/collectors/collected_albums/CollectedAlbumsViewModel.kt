package co.edu.uniandes.vinylsg12.ui.collectors.collected_albums

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinylsg12.common.api.interfaces.CollectorAlbumService
import co.edu.uniandes.vinylsg12.common.api.models.Album
import co.edu.uniandes.vinylsg12.common.api.models.CollectedAlbum
import co.edu.uniandes.vinylsg12.common.api.models.Collector
import co.edu.uniandes.vinylsg12.common.api.services.RetrofitCollectorAlbumService
import co.edu.uniandes.vinylsg12.common.session.UserSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CollectedAlbumsViewModel: ViewModel() {

    private val service: CollectorAlbumService = RetrofitCollectorAlbumService()
    private val collectorUser: Collector? = UserSession.collector

    private val _collectedAlbums = MutableLiveData<List<CollectedAlbum>>()
    val collectedAlbums: LiveData<List<CollectedAlbum>> = _collectedAlbums

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    public fun fetchData() {

        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    service.albums(collectorId = collectorUser?.id ?: 0,
                        onComplete = { fetchedAlbums ->
                            _collectedAlbums.postValue(fetchedAlbums)
                            val albums = fetchedAlbums.map {
                                it.album!!
                            }
                            _albums.postValue(albums)
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