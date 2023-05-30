package co.edu.uniandes.vinylsg12.common.api.models

data class Performer(
    val id: Int? = null,
    val name: String,
    val image: String,
    val description: String,
    var birthdate: String
)
