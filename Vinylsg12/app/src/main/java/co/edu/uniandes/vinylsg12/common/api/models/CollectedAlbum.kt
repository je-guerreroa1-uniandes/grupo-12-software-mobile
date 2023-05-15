package co.edu.uniandes.vinylsg12.common.api.models

data class CollectedAlbum(
    val id: Int? = null,
    val price: Int,
    val status: String,
    val album: Album? = null,
    val collector: Collector? = null
    )