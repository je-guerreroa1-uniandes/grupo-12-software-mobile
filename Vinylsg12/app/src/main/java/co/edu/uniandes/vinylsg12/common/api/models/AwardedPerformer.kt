package co.edu.uniandes.vinylsg12.common.api.models

data class AwardedPerformer(
    val id: Int? = null,
    var premiationDate: String,
    val prize: Prize? = null,
    var performer: Performer? = null
)