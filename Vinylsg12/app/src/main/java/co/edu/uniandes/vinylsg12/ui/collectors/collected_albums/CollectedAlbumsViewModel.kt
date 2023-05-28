package co.edu.uniandes.vinylsg12.ui.collectors.collected_albums

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinylsg12.common.api.interfaces.AlbumService
import co.edu.uniandes.vinylsg12.common.api.interfaces.CollectorAlbumService
import co.edu.uniandes.vinylsg12.common.api.models.Album
import co.edu.uniandes.vinylsg12.common.api.models.CollectedAlbum
import co.edu.uniandes.vinylsg12.common.api.models.Collector
import co.edu.uniandes.vinylsg12.common.api.services.RetrofitAlbumService
import co.edu.uniandes.vinylsg12.common.api.services.RetrofitCollectorAlbumService
import co.edu.uniandes.vinylsg12.common.session.UserSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CollectedAlbumsViewModel: ViewModel() {

    private val collectorAlbumService: CollectorAlbumService = RetrofitCollectorAlbumService()
    private var albumService: AlbumService = RetrofitAlbumService()
    private val collectorUser: Collector? = UserSession.collector

    private val _collectedAlbums = MutableLiveData<List<CollectedAlbum>>()
    val collectedAlbums: LiveData<List<CollectedAlbum>> = _collectedAlbums

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    fun fetchCollectedAlbums() {

        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    collectorAlbumService.albums(collectorId = collectorUser?.id ?: 0,
                        onComplete = { fetchedAlbums ->
                            _collectedAlbums.postValue(fetchedAlbums)
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

    fun fetchAlbums() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    albumService.albums(
                        onComplete = { fetchedAlbums ->
                            val collectedIds= collectedAlbums.value?.map { it.album?.id } ?: emptyList()
                            val filteredAlbums= fetchedAlbums.filter { it.id !in collectedIds }
                            _albums.postValue(filteredAlbums)
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

    fun addToCollection(albumId: Int, price: Int?, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    collectorAlbumService.add(
                        albumId= albumId,
                        collectorId= collectorUser?.id ?: 0,
                        price= price ?: 0,
                        status= "Active",
                        onComplete = {
                            onSuccess()
                        },
                        onError = {
                            onError(it)
                        }
                    )
                }
            } catch (e: Exception) {
                Log.e("MyTag", "Error adding album: ${e.message}", e)
            }
        }
    }
}