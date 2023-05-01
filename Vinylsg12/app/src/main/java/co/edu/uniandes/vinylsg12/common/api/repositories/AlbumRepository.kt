package co.edu.uniandes.vinylsg12.common.api.repositories

import android.app.Application
import co.edu.uniandes.vinylsg12.common.api.interfaces.AlbumService
import co.edu.uniandes.vinylsg12.common.api.models.Album
import java.lang.Exception

class AlbumRepository (val application: Application, val albumService: AlbumService) {
    fun refreshData(callback: (List<Album>)->Unit, onError: (Exception)->Unit) {
        albumService.getAlbums(callback, onError)
        // TODO: Room cache or persistent to be implemented here
    }
}