package co.edu.uniandes.vinylsg12.common.api.interfaces

import co.edu.uniandes.vinylsg12.common.api.models.AwardedPerformer

interface AwardedPerformerService {
    fun add(musicianId: Int, prizeId: Int, awardDate: String, onComplete: (resp: AwardedPerformer?) -> Unit, onError: (error: Exception) -> Unit)
}