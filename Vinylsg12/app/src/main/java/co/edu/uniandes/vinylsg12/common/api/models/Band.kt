package co.edu.uniandes.vinylsg12.common.api.models

data class Band(
    val id: Int? = null,
    val name: String,
    val image: String,
    val description: String,
    val creationDate: String,
//    val albums: Array<Album> = emptyArray<Album>()
    val musicians: List<Musician> = emptyList()
//    val performerPrizes: Array<Prize>
)
