package co.edu.uniandes.vinylsg12.common.api.interfaces

import co.edu.uniandes.vinylsg12.common.api.models.AwardedMusician

interface AwardedMusicianService {
    fun add(musicianId: Int, prizeId: Int, onComplete: (resp: List<AwardedMusician>) -> Unit)
}