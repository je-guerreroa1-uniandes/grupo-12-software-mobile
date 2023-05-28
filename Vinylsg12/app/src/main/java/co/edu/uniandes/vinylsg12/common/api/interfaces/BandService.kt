package co.edu.uniandes.vinylsg12.common.api.interfaces

import co.edu.uniandes.vinylsg12.common.api.models.Band
import co.edu.uniandes.vinylsg12.common.api.models.Musician

interface BandService {
    fun bands(onComplete: (resp: List<Band>)->Unit, onError: (error: Exception)->Unit)
    fun band(id: Int, onComplete: (resp: Band?) -> Unit, onError: (error: Exception) -> Unit)
    fun add(musicianId: Int, bandId: Int, onComplete: (resp: Musician?) -> Unit, onError: (error: Exception) -> Unit)
}