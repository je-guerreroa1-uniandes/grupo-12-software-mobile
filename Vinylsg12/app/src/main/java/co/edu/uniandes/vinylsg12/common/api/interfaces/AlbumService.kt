package co.edu.uniandes.vinylsg12.common.api.interfaces

import co.edu.uniandes.vinylsg12.common.api.models.Album

interface AlbumService {
    //@GET("albums")
    fun getAlbums(onComplete:(resp:List<Album>)->Unit, onError: (error: Exception)->Unit)
}