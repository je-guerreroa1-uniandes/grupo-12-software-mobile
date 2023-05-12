package co.edu.uniandes.vinylsg12.common.api.models

data class Album(
    val id: Int? = null,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String
)
