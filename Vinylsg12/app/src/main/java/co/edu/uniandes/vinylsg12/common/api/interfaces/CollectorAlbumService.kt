package co.edu.uniandes.vinylsg12.common.api.interfaces

import co.edu.uniandes.vinylsg12.common.api.models.CollectedAlbum

interface CollectorAlbumService {
    fun albums(collectorId: Int, onComplete:(resp:List<CollectedAlbum>)->Unit, onError: (error: Exception)->Unit)
    fun saveAlbum(albumId: Int, collectorId: Int, onComplete: (resp: CollectedAlbum?) -> Unit, onError: (error: Exception) -> Unit)
}