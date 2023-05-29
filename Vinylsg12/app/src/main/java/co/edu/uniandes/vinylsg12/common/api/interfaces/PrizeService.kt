package co.edu.uniandes.vinylsg12.common.api.interfaces

import co.edu.uniandes.vinylsg12.common.api.models.Prize

interface PrizeService {
    fun prizes(onComplete:(resp: List<Prize>) -> Unit, onError: (error: Exception) -> Unit)
    fun create(
        name: String,
        description: String,
        organization: String,
        onComplete: (resp: Prize?) -> Unit,
        onError: (error: Exception) -> Unit
    )
}