package co.edu.uniandes.vinylsg12.common.api.interfaces

import co.edu.uniandes.vinylsg12.common.api.models.Album

interface AlbumService {
    fun getAlbums(onComplete:(resp:List<Album>)->Unit, onError: (error: Exception)->Unit)

    fun getAlbum(id: Int, onComplete:(resp:Album?)->Unit, onError: (error: Exception)->Unit)
}