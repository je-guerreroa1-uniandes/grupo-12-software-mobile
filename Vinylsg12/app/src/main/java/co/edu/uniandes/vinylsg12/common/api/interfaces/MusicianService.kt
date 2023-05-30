package co.edu.uniandes.vinylsg12.common.api.interfaces

import co.edu.uniandes.vinylsg12.common.api.models.Band
import co.edu.uniandes.vinylsg12.common.api.models.Musician
import java.lang.Exception

interface MusicianService {
    fun musicians(onComplete: (resp: List<Musician>) -> Unit, onError: (error: Exception) -> Unit)
    fun musician(id: Int, onComplete: (resp: Musician?) -> Unit, onError: (error: Exception) -> Unit)
}