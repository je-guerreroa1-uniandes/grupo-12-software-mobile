package co.edu.uniandes.vinylsg12.common.api.models

data class CollectedAlbum(
    val id: Int,
    val price: Int,
    val status: String,
    val album: Album,
    val collector: Collector
    )