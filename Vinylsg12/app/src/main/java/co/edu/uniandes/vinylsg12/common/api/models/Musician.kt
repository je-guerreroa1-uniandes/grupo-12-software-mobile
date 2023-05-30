package co.edu.uniandes.vinylsg12.common.api.models

data class Musician(
    val id: Int? = null,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String,
    val band: Band? = null,
    val albums: List<Album> = emptyList(),
    val performerPrizes: List<AwardedPerformer> = emptyList()
)
