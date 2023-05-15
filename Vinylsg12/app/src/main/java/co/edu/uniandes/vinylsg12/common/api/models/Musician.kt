package co.edu.uniandes.vinylsg12.common.api.models

data class Musician(
    val id: Int? = null,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String
//    val albums: Array<Album> = emptyArray<Album>(),
//    val performerPrizes: String
)
