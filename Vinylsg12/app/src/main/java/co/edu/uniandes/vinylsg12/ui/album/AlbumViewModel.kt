package co.edu.uniandes.vinylsg12.ui.album

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinylsg12.common.api.interfaces.AlbumService
import co.edu.uniandes.vinylsg12.common.api.models.Album
import co.edu.uniandes.vinylsg12.common.api.services.RetrofitAlbumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumViewModel: ViewModel() {

    private val service: AlbumService = RetrofitAlbumService()

    private val _text = MutableLiveData<String>().apply {
        value = "Album title"
    }
    val text: LiveData<String> = _text

    private val _album = MutableLiveData<Album>().apply {
        value = null
    }
    val album: LiveData<Album?> = _album

    public fun fetchAlbum(albumId: Int) {
        viewModelScope.launch {
            try {
                service.getAlbum(albumId,
                    onComplete = { album ->
                        _album.postValue(album)
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