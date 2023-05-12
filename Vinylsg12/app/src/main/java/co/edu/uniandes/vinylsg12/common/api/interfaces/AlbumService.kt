package co.edu.uniandes.vinylsg12.common.api.interfaces

import co.edu.uniandes.vinylsg12.common.api.models.Album

interface AlbumService {
    fun albums(onComplete:(resp:List<Album>)->Unit, onError: (error: Exception)->Unit)

    fun album(id: Int, onComplete:(resp:Album?)->Unit, onError: (error: Exception)->Unit)
}