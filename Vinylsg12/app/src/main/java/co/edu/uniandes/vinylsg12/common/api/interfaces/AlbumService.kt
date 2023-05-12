package co.edu.uniandes.vinylsg12.common.api.interfaces

import co.edu.uniandes.vinylsg12.common.api.models.Album
import co.edu.uniandes.vinylsg12.common.api.models.Collector

interface AlbumService {
    fun albums(onComplete:(resp:List<Album>)->Unit, onError: (error: Exception)->Unit)
    fun album(id: Int, onComplete:(resp:Album?)->Unit, onError: (error: Exception)->Unit)
    fun create(
        name: String,
        cover: String,
        releaseDate: String,
        description: String,
        genre: String,
        recordLabel: String,
        onComplete: (resp: Album?) -> Unit,
        onError: (error: Exception) -> Unit
    )
}